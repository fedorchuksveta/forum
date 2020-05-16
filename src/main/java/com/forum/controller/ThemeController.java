package com.forum.controller;

import com.forum.model.Theme;
import com.forum.model.User;
import com.forum.service.ThemeService;
import com.forum.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presentation")
@Slf4j
public class ThemeController {

    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping("{id}")
    public Theme getOne(@PathVariable Long id) {
        return themeService.getOne(id);
    }

    @GetMapping("all")
    public List<Theme> findAll() {
        return themeService.findAll();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        themeService.delete(id);
    }

    @PostMapping
    public Theme save(@RequestBody Theme presentation) {
        return themeService.create(presentation);
    }

}