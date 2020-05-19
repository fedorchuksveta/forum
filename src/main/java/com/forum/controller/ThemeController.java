package com.forum.controller;

import com.forum.model.Theme;
import com.forum.model.Topic;
import com.forum.service.ThemeService;
import com.forum.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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


    @GetMapping
    public String homePage(Model model) {
        List<Theme> themes = themeService.findAll();
        model.addAttribute("themes", themes);
        return "themePage";
    }

    @GetMapping("/new")
    public String addTheme(Model model) {
        Theme theme = new Theme();
        model.addAttribute("theme", theme);
        return "themeAdd";
    }

    @PostMapping("/new")
    public String savePresentation(@ModelAttribute("theme") Theme theme) {
        themeService.create(theme);
        return "redirect:/theme";
    }

    @GetMapping("/{id}/edit")
    public String editThemeById(Model model, @PathVariable("id") Long id) {

        Theme theme = themeService.getOne(id);
        model.addAttribute("theme", theme);

        return "themeEdit";
    }

    @PostMapping
    public String editThemeById(Theme theme) {
        themeService.update(theme);
        return "redirect:/theme";
    }

    @GetMapping("/{id}/delete")
    public String deleteThemeById(RedirectAttributes attributes, @PathVariable Long id) {
        List<Topic> topics = topicService.findAllByTheme(id);
        if (!topics.isEmpty()) {
            attributes.addFlashAttribute("error", "Theme can't be deleted, because has topics");
            return "redirect:/theme";
        }
        themeService.delete(id);
        return "redirect:/theme";
    }

    @GetMapping("/{id}/topic")
    public String getTaken(@PathVariable Long id, Model model) {
        List<Topic> topics = topicService.findAllByTheme(id);
        model.addAttribute("topics", topics);
        model.addAttribute("themeId", id);
        return "topicPage";
    }

}