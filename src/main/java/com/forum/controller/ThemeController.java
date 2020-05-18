package com.forum.controller;

import com.forum.model.Theme;
import com.forum.model.Topic;
import com.forum.service.ThemeService;
import com.forum.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/theme")
@Slf4j
public class ThemeController {

    private final ThemeService themeService;

    public ThemeController(ThemeService themeService, TopicService topicService) {
        this.themeService = themeService;
        this.topicService = topicService;
    }

    private final TopicService topicService;


    @GetMapping("/themePage")
    public String homePage(Model model) {
        List<Theme> themes = themeService.findAll();
        model.addAttribute("themes", themes);
        return "themePage";
    }

    @GetMapping("/addTheme")
    public String addTheme(Model model) {
        Theme theme = new Theme();
        model.addAttribute("theme", theme);
        return "themeAdd";
    }

    @PostMapping("/addTheme")
    public String savePresentation(Model model,
                                   @ModelAttribute("theme") Theme theme) {
        themeService.create(theme);
        return "redirect:/theme/themePage";
    }

    @GetMapping("{id}")
    public Theme getOne(@PathVariable Long id) {
        return themeService.getOne(id);
    }

    @GetMapping("/editTheme/{id}")
    public String editThemeById(Model model, @PathVariable("id") Optional<Long> id) {

        Theme theme = themeService.getOne(id.get());
        model.addAttribute("theme", theme);

        return "themeEdit";
    }

    @PostMapping("/editTheme")
    public String editThemeById(Theme theme) {
        themeService.update(theme);
        return "redirect:/theme/themePage";
    }

    @GetMapping("/deleteTheme/{id}")
    public String deleteThemeById(@PathVariable Long id) {
        themeService.delete(id);
        return "redirect:/theme/themePage";
    }
//
//    @PostMapping("/deleteTheme")
//    public String deleteThemeById(Theme theme) {
//
//        return "redirect:/theme/themePage";
//    }

    @GetMapping("/topicTheme/{id}")
    public String getTaken(@PathVariable Long id, Model model) {
        List<Topic> topics = topicService.findAllByTheme(id);
        model.addAttribute("topics", topics);
        model.addAttribute("themeId", id);
        return "topicPage";
    }

}