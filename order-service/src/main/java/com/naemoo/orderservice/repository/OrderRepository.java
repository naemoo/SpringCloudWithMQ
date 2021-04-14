package com.naemoo.orderservice.repository;

import com.naemoo.orderservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    public OrderEntity findByOrderId(String orderId);

    public List<OrderEntity> findByUserId(String userId);
}
