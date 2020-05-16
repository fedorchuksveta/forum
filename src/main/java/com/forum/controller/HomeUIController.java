package com.forum.controller;

import com.forum.model.User;
import com.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("/")
public class HomeUIController {


    private final UserService userService;

    public HomeUIController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/registration")
    public String registerCustomer(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        return "registerUser";

    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerCustomerPost(@Valid @ModelAttribute("user") User user, BindingResult result,
                                       Model model) {

        if (result.hasErrors()) {
            return "registerUser";
        }

        userService.create(user);

        return "registerUserSuccess";

    }

}