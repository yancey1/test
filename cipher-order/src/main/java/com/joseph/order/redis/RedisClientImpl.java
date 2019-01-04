package com.joseph.order.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

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
}
