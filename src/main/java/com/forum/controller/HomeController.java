package com.forum.controller;

import com.forum.model.User;
import com.forum.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public User getOne(@PathVariable Long id) {
        return userService.getOne(id);
    }

    @GetMapping("all")
    public List<User> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }


}