package com.springboot.project.service;

import com.springboot.project.model.Order;
import com.springboot.project.model.User;
import com.springboot.project.request.OrderReq;

import java.util.List;

public interface OrderService {
    public Order createOrder(OrderReq order, User user) throws Exception;
    public Order updateOrder(Long orderId,String orderStatus) throws Exception;
    public void cancelOrder(Long orderId) throws Exception;
    public List<Order> getUserOrder(Long userId) throws Exception;
    public List<Order> getRestaurantOrders(Long restaurantId,String orderStatus) throws Exception;
    public  Order findOrderById (Long orderId) throws Exception;
}
