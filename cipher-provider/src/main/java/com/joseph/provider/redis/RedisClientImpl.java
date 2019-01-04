package com.joseph.provider.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class RedisClientImpl implements RedisClient {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public String set(String key, String value) {
        Jedis jedis = getJedis();
        String result = jedis.set(key, value);
        jedis.close();

        return result;
    }

    @Override
    public String set(byte[] key, byte[] value) {
        Jedis jedis = getJedis();
        String result = jedis.set(key, value);
        jedis.close();

        return result;
    }

    @Override
    public String get(String key) {
        Jedis jedis = getJedis();
        String result = jedis.get(key);
        jedis.close();

        return result;
    }

    @Override
    public byte[] get(byte[] key) {
        Jedis jedis = getJedis();
        byte[] result = jedis.get(key);
        jedis.close();

        return result;
    }

    @Override
    public Long del(String key) {
        Jedis jedis = getJedis();
        Long result = jedis.del(key);
        jedis.close();

        return result;
    }

    @Override
    public Long del(byte[] key) {
        Jedis jedis = getJedis();
        Long result = jedis.del(key);
        jedis.close();

        return result;
    }

    @Override
    public Long del(String... keys) {
        Jedis jedis = getJedis();
        Long result = jedis.del(keys);
        jedis.close();

        return result;
    }

    @Override
    public Long del(byte[]... keys) {
        Jedis jedis = getJedis();
        Long result = jedis.del(keys);
        jedis.close();

        return result;
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis = getJedis();
        Boolean result = jedis.exists(key);
        jedis.close();

        return result;
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = getJedis();
        Long result = jedis.expire(key, seconds);
        jedis.close();

        return result;
    }

    @Override
    public Long expire(byte[] key, int seconds) {
        Jedis jedis = getJedis();
        Long result = jedis.expire(key, seconds);
        jedis.close();

        return result;
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = getJedis();
        Long result = jedis.ttl(key);
        jedis.close();

        return result;
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = getJedis();
        Long result = jedis.incr(key);
        jedis.close();

        return result;
    }

    @Override
    public Long hset(String key, String field, String value) {
        Jedis jedis = getJedis();
        Long result = jedis.hset(key, field, value);
        jedis.close();

        return result;
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis = getJedis();
        String result = jedis.hget(key, field);
        jedis.close();

        return result;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = getJedis();
        Map<String, String> result = jedis.hgetAll(key);
        jedis.close();

        return result;
    }

    @Override
    public Long hdel(String key, String... fields) {
        Jedis jedis = getJedis();
        Long result = jedis.hdel(key, fields);
        jedis.close();

        return result;
    }

    @Override
    public void setObj(String key,Object obj) {
        Jedis jedis = getJedis();
        byte[] keyByte=key.getBytes();
        byte[] objByte=SerializeUtil.serialize(obj);
        jedis.set(keyByte, objByte);
        jedis.close();
    }

    @Override
    public Object getObj(String key) {
        Jedis jedis = getJedis();
        byte[] bytes = jedis.get(key.getBytes());
        jedis.close();
        return SerializeUtil.unserialize(bytes);
    }


    public Jedis getJedis(){
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

    /**
     * 分布式加锁
     * @param locaName  锁的key
     * @param acquireTimeout  获取超时时间
     * @param timeout   锁的超时时间
     * @return 锁标识
     */
    public String lockWithTimeout(String locaName,
                                  long acquireTimeout, long timeout) {
        Jedis conn = null;
        String retIdentifier = null;
        try {
            // 获取连接
            conn = jedisPool.getResource();
            // 随机生成一个value
            String identifier = UUID.randomUUID().toString();
            // 锁名，即key值
            String lockKey = "lock:" + locaName;
            // 超时时间，上锁后超过此时间则自动释放锁
            int lockExpire = (int)(timeout / 1000);

            // 获取锁的超时时间，超过这个时间则放弃获取锁
            long end = System.currentTimeMillis() + acquireTimeout;
            while (System.currentTimeMillis() < end) {
                if (conn.setnx(lockKey, identifier) == 1) {
                    conn.expire(lockKey, lockExpire);
                    // 返回value值，用于释放锁时间确认
                    retIdentifier = identifier;
                    return retIdentifier;
                }
                // 返回-1代表key没有设置超时时间，为key设置一个超时时间
                if (conn.ttl(lockKey) == -1) {
                    conn.expire(lockKey, lockExpire);
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (JedisException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return retIdentifier;
    }

    /**
     * 分布式释放锁
     * @param lockName 锁的key
     * @param identifier    释放锁的标识
     * @return
     */
    public boolean releaseLock(String lockName, String identifier) {
        Jedis conn = null;
        String lockKey = "lock:" + lockName;
        boolean retFlag = false;
        try {
            conn = jedisPool.getResource();
            while (true) {
                // 监视lock，准备开始事务
                conn.watch(lockKey);
                // 通过前面返回的value值判断是不是该锁，若是该锁，则删除，释放锁
                if (identifier.equals(conn.get(lockKey))) {
                    Transaction transaction = conn.multi();
                    transaction.del(lockKey);
                    List<Object> results = transaction.exec();
                    if (results == null) {
                        continue;
                    }
                    retFlag = true;
                }
                conn.unwatch();
                break;
            }
        } catch (JedisException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return retFlag;
    }
}
