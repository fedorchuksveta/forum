package com.forum.controller;

import com.forum.model.User;
import com.forum.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
public class HomeUIController {


    private final UserService userService;
    private static final String SUCCESSFULLY_REGISTERED_MESSAGE = "Користувача вдало зареєстровано!";
    private static final String NICK_NAME_ALREADY_USED_MESSAGE = "Дане ім'я вже використовується";

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
    public String registerCustomerPost(@Valid User user, BindingResult result,
                                       Model model) {

        if (result.hasErrors() || userExists(user, result)) {
            return "registration";
        }

        user = userService.create(user);

        model.addAttribute("successMessage", SUCCESSFULLY_REGISTERED_MESSAGE);
        model.addAttribute("user", user);
        return "themePage";
    }


    private boolean userExists(User user, BindingResult bindingResult) {
        boolean resultNickName = userService.findUserByNickName(user.getNickName()).isPresent();
        if (resultNickName) {
            bindingResult.rejectValue("nickName", "error.user", NICK_NAME_ALREADY_USED_MESSAGE);
        }

        return resultNickName;
    }

}