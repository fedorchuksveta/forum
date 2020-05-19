package com.forum.controller;

import com.forum.model.Comment;
import com.forum.model.Theme;
import com.forum.model.Topic;
import com.forum.service.CommentService;
import com.forum.service.ThemeService;
import com.forum.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/topic")
@Slf4j
public class TopicController {

    private final TopicService topicService;

    private final CommentService commentService;

    private final ThemeService themeService;

    public TopicController(TopicService topicService, CommentService commentService, ThemeService themeService) {
        this.topicService = topicService;
        this.commentService = commentService;
        this.themeService = themeService;
    }

    @GetMapping("/{id}")
    public String addTopic(Model model, @PathVariable String id) {
        Theme theme = themeService.findThemeById(Long.parseLong(id));
        Topic topic = new Topic();
        topic.setTheme(theme);
        model.addAttribute("topic", topic);
        return "topicAdd";
    }

    @PostMapping("/{id}")
    public String saveTopic(@PathVariable Long id, @ModelAttribute("topic") Topic topic) {
        Theme theme = themeService.findThemeById(id);
        topic.setTheme(theme);
        topicService.create(topic);
        return "redirect:/theme/{id}/topic";
    }

    @GetMapping("/{id}/forum")
    public String getTaken(@PathVariable Long id, Model model) {
        List<Comment> comments = commentService.findAllByTopic(id);
        model.addAttribute("comments", comments);
        model.addAttribute("topicId", id);
        return "forumTopic";
    }
}
