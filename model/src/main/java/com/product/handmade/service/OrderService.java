package com.product.handmade.service;

import com.product.handmade.DTO.OrderDTO;
import com.product.handmade.DTO.UserDTO;
import com.product.handmade.model.Order;
import com.product.handmade.model.User;
import com.product.handmade.repo.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public OrderDTO saveOrder(Order order){
        var order1= Order.builder()
                .id(order.getId())
                .user(order.getUser())
                .number(order.getNumber())
                .address(order.getAddress())
                .zip(order.getZip())
                .city(order.getCity())
                .state(order.getState())
                .build();
        orderRepository.save(order1);
        return OrderDTO.from(order1);
    }
}
