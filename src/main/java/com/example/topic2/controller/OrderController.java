package com.example.topic2.controller;

import com.example.topic2.entity.Taco;
import com.example.topic2.entity.TacoOrder;
import com.example.topic2.repository.OrderRepository;
import com.example.topic2.security.User;
import com.example.topic2.service.OrderBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderBroker orderBroker;

    @Autowired
    public OrderController(OrderRepository orderRepository, OrderBroker orderBroker) {
        this.orderRepository = orderRepository;
        this.orderBroker = orderBroker;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String  processOrder(TacoOrder order,
                                SessionStatus sessionStatus) {

        order.setPlacedAt(new Date());
        for (Taco taco: order.getTacos()) {
            taco.setCreatedAt(order.getPlacedAt());
        }
        order.setOwner((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        orderRepository.save(order);
        orderBroker.sendOrderToBroker(order);

        sessionStatus.setComplete();


        return "redirect:/";
    }
}
