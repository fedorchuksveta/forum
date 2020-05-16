package com.forum.controller;

import com.forum.model.User;
import com.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterUIController {

    private final UserService userService;

    public RegisterUIController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("/registerr")
    public String registerCustomer(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "registerUser";

    }
}