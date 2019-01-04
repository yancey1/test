package com.joseph.provider.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.joseph.api.dto.TestDTO;
import com.joseph.api.service.BizService;
import com.joseph.provider.dao.mapper.ProxyStartegyMapper;
import com.joseph.provider.dao.po.Test;
import com.joseph.provider.redis.RedisClient;
import com.joseph.provider.redis.RedisClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class BizServiceImpl implements BizService {

	private static final Logger logger = LoggerFactory.getLogger(BizServiceImpl.class);

	@Autowired
	private ProxyStartegyMapper proxyStartegyMapper;

	@Autowired
	private RedisClient redisClient;

	private ExecutorService executors= Executors.newFixedThreadPool(10);

	@Override
	public TestDTO test(String name) {
		TestDTO testDto=new TestDTO();
		Test test= (Test) redisClient.getObj("test");
		if(test==null){
			test=proxyStartegyMapper.selectCodecConfig();
			if(test!=null){
				redisClient.setObj("test",test);
			}
		}
		testDto.setName("hello:"+test.getName());
		return testDto;
	}

	@Override
	public void secondKillTest(int count){
		TestThread testThread=new TestThread(500, (RedisClientImpl) redisClient);
		for(int i=0;i<count;i++){
			executors.execute(testThread);
		}
	}
}
