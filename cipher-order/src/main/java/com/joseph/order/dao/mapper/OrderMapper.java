package com.joseph.order.dao.mapper;

import com.joseph.order.domain.OrderDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 获取订单信息
 * 
 * @author yanwei
 */
public interface OrderMapper {

	/**
	 * 订单信息管理
	 * 
	 */
	 List<OrderDO> listOrders(OrderDO orderDO);

	 void insertOrder(OrderDO orderDO);

	 void updateOrder(OrderDO orderDO);

	 void deleteOrder(OrderDO orderDO);
}
