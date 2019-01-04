package com.joseph.provider.dao.mapper;

import com.joseph.provider.dao.po.Test;
import org.springframework.stereotype.Repository;

/**
 * 获取配置信息
 * 
 * @author yanwei
 */
public interface ProxyStartegyMapper {

	/**
	 * 获取数据库配置信息
	 * 
	 */
	public Test selectCodecConfig();

}
