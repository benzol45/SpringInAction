package com.example.topic2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterUserController {
    private final UserService userService;

    @Autowired
    public RegisterUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute UserDTO userDto) {
        userService.registerNewUser(userDto.toUser());

        return "redirect:/login";
    }


}
