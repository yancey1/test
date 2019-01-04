package com.joseph.order.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.protocol.dubbo.telnet.CurrentTelnetHandler;
import com.joseph.api.dto.OrderDTO;
import com.joseph.api.service.OrderService;
import com.joseph.order.dao.mapper.OrderMapper;
import com.joseph.order.domain.OrderDO;
import com.joseph.order.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedisClient redisClient;

    @Override
    public List<OrderDTO> listOrders(OrderDTO orderDTO) {
        OrderDO orderDO = new OrderDO();
        List<OrderDO> orderDOList = orderMapper.listOrders(orderDO);
        List<OrderDTO> orderDTOList=buildDTO(orderDOList);
        return orderDTOList;
    }

    @Override
    public Integer insertOrder(OrderDTO orderDTO) {
        try{
            OrderDO orderDO = new OrderDO();
            orderDO.setGoodsId(orderDTO.getGoodsId());
            orderDO.setGoodsNum(orderDTO.getGoodsNum());
            orderDO.setOrderName(orderDTO.getOrderName());
            orderMapper.insertOrder(orderDO);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public Integer updateOrder(OrderDTO orderDTO) {
        try{
            OrderDO orderDO = new OrderDO();
            orderDO.setGoodsId(orderDTO.getGoodsId());
            orderDO.setGoodsNum(orderDTO.getGoodsNum());
            orderDO.setOrderName(orderDTO.getOrderName());
            orderMapper.insertOrder(orderDO);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public Integer deleteOrder(OrderDTO orderDTO) {
        return null;
    }

    public List<OrderDTO> buildDTO(List<OrderDO> orderDOList) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        if (orderDOList != null) {
            for (OrderDO orderDO : orderDOList) {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setGoodsId(orderDO.getGoodsId());
                orderDTO.setGoodsNum(orderDO.getGoodsNum());
                orderDTO.setOrderName(orderDO.getOrderName());
                orderDTOList.add(orderDTO);
            }
        }
        return orderDTOList;
    }
}
