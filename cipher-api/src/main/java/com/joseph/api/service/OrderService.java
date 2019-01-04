package com.joseph.api.service;

import com.joseph.api.dto.OrderDTO;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrderService {

    List<OrderDTO> listOrders(OrderDTO orderDTO);

    Integer insertOrder(OrderDTO orderDTO);

    Integer updateOrder(OrderDTO orderDTO);

    Integer deleteOrder(OrderDTO orderDTO);
}
