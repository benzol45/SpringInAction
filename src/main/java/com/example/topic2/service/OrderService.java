package com.example.topic2.service;

import com.example.topic2.Parameters;
import com.example.topic2.entity.TacoOrder;
import com.example.topic2.repository.OrderRepository;
import com.example.topic2.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private Parameters parameters;

    @Autowired
    public OrderService(OrderRepository orderRepository, Parameters parameters) {
        this.orderRepository = orderRepository;
        this.parameters = parameters;
    }

    public List<TacoOrder> getTacoListByUser(User user) {
        if (user==null) {
            return Collections.emptyList();
        }

        return orderRepository.findByOwner(user, Pageable.ofSize(parameters.getListSize1()));

    }
}
