package com.naemoo.orderservice.service;

import com.naemoo.orderservice.dto.OrderDto;
import com.naemoo.orderservice.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    OrderDto getOrderByOrderId(String orderId);

    List<OrderEntity> getAllOrder();

    List<OrderEntity> getOrderByUserId(String userId);
}