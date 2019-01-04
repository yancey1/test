package com.joseph.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.joseph.api.dto.TestDTO;
import com.joseph.api.service.BizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BizController {

	private static final Logger logger = LoggerFactory.getLogger(BizController.class);

	@Reference
	private BizService bizService;

	@RequestMapping(value = "/test")
	public void test(int  count){


		bizService.secondKillTest(count);
	}

}
