package com.naemoo.orderservice.controller;

import com.naemoo.orderservice.dto.OrderDto;
import com.naemoo.orderservice.entity.OrderEntity;
import com.naemoo.orderservice.service.OrderService;
import com.naemoo.orderservice.vo.RequestOrder;
import com.naemoo.orderservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;


    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId,
                                                     @RequestBody @Valid RequestOrder requestOrder, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        OrderDto orderDto = modelMapper.map(requestOrder, OrderDto.class);
        orderDto.setUserId(userId);
        OrderDto createdOrder = orderService.createOrder(orderDto);
        ResponseOrder resBody = modelMapper.map(createdOrder, ResponseOrder.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(resBody);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrders(@PathVariable("userId") String userId) {
        List<OrderEntity> orders = orderService.getOrderByUserId(userId);
        List<ResponseOrder> body = orders.stream()
                .map(o -> modelMapper.map(o, ResponseOrder.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(body);
    }
}
