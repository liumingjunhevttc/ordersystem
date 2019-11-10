package com.baidu.repository;

import com.baidu.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderRepository {
    public int orderDishes(Order order);
    public List<Order> selectByPage(Map<String,Object> params);
    public int selectByCount(Map<String,Object> params);
    public int deleteOrder(Integer id);
    public int receiveOrder(Integer id);
    public int sendOrder(Integer id);
    public int receiveOrderObj(Integer id);
}
