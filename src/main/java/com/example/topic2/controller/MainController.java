package com.example.topic2.controller;

import com.example.topic2.integration.FileGateway;
import com.example.topic2.security.User;
import com.example.topic2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    private OrderService orderService;
    //private RabbitTemplate rabbitTemplate;

    @Autowired
    public MainController(OrderService orderService) {
        this.orderService = orderService;
        //this.rabbitTemplate = rabbitTemplate;

    }

    @GetMapping
    public String getMainPage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("order_list", orderService.getTacoListByUser(user));

        //TEST
        //TacoOrder order = rabbitTemplate.receiveAndConvert("orders", new ParameterizedTypeReference<TacoOrder>() {});
        //System.out.println(order);
        //--test

        return "main";
    }
}
