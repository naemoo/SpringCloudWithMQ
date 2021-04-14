package com.naemoo.orderservice.service;

import com.naemoo.orderservice.dto.OrderDto;
import com.naemoo.orderservice.entity.OrderEntity;
import com.naemoo.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;


    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getUnitPrice() * orderDto.getQty());

        OrderEntity orderEntity = OrderEntity.builder()
                .unitPrice(orderDto.getUnitPrice())
                .totalPrice(orderDto.getTotalPrice())
                .qty(orderDto.getQty())
                .productId(orderDto.getProductId())
                .userId(orderDto.getUserId())
                .orderId(orderDto.getOrderId())
                .build();

        orderRepository.save(orderEntity);

        OrderDto returnOrder = modelMapper.map(orderEntity, OrderDto.class);
        return returnOrder;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        OrderEntity findOrder = orderRepository.findByOrderId(orderId);
        if (findOrder == null) {
            throw new RuntimeException("찾을 수 없는 OrderId 입니다.");
        }
        OrderDto returnOrder = modelMapper.map(findOrder, OrderDto.class);
        return returnOrder;
    }

    @Override
    public List<OrderEntity> getAllOrder() {
        return null;
    }

    @Override
    public List<OrderEntity> getOrderByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
