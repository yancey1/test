package com.joseph.api.service;

import com.joseph.api.dto.TestDTO;

public interface BizService {

	TestDTO test(String name);

	void secondKillTest(int count);
}
