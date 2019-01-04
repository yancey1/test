package com.joseph.provider.dao.mapper;

import com.joseph.provider.dao.po.Test;
import com.joseph.provider.domain.GoodsDO;

import java.util.List;

/**
 * 获取商品信息
 * 
 * @author yanwei
 */
public interface GoodsMapper {

	List<GoodsDO> listGoods(GoodsDO goodsDO);

	void insertGoods(GoodsDO goodsDO);

	void updateGoods(GoodsDO goodsDO);

	void deleteGoods(GoodsDO goodsDO);
}
