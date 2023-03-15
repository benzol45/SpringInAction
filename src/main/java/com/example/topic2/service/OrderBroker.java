package com.example.topic2.service;

import com.example.topic2.entity.TacoOrder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Order;

@Service
public class OrderBroker {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public OrderBroker(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrderToBroker(TacoOrder order) {
        rabbitTemplate.convertAndSend("","", order);
    }
}
