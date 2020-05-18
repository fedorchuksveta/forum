package com.forum.controller;

import com.forum.model.Theme;
import com.forum.service.ThemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/presentation")
@Slf4j
public class ThemeController {

    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping
    public String getThemePage(Principal principal) {
        return "Hello, " + principal.getName();
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