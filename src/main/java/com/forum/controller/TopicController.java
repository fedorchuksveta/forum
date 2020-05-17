package com.forum.controller;

import com.forum.model.Comment;
import com.forum.model.Theme;
import com.forum.model.Topic;
import com.forum.model.User;
import com.forum.service.CommentService;
import com.forum.service.ThemeService;
import com.forum.service.TopicService;
import com.forum.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/topic")
@Slf4j
public class TopicController {

    private final TopicService topicService;

    private final UserService userService;

    private final CommentService commentService;

    private final ThemeService themeService;

    public TopicController(TopicService topicService, UserService userService, CommentService commentService, ThemeService themeService) {
        this.topicService = topicService;
        this.userService = userService;
        this.commentService = commentService;
        this.themeService = themeService;
    }

    @GetMapping("/addTopic/{id}")
    public String addTopic(Model model, @PathVariable String id) {
        Theme theme = themeService.findThemeById(Long.parseLong(id));
        Topic topic = new Topic();
        topic.setTheme(theme);
        model.addAttribute("topic", topic);
        return "topicAdd";
    }

    @PostMapping("/addTopic/{id}")
    public String saveTopic(Model model, @PathVariable Long id,
                                   @ModelAttribute("topic") Topic topic) {
        Theme theme = themeService.findThemeById(id);
        topic.setTheme(theme);
        topicService.create(topic);
        return "redirect:/theme/topicTheme/{id}";
    }

    @GetMapping("/topic/{id}")
    public String getTaken(@PathVariable Long id, Model model) {
        List<Comment> comments = commentService.findAllByTopic(id);
        model.addAttribute("comments", comments);
        model.addAttribute("topicId", id);
        return "forumTopic";
    }

    @GetMapping("/addComment/{id}")
    public String addComment(Model model, @PathVariable Long id, Principal principal) {
        Topic topic = topicService.findTopicById(id);
        User user = userService.findUserByFirstName(principal.getName());
        Comment comment = new Comment();
        comment.setTopic(topic);
        comment.setUser(user);
        model.addAttribute("comment", comment);
        return "commentAdd";
    }

    @PostMapping("/addComment/{id}")
    public String saveComment(Model model, @PathVariable Long id,
                                   @ModelAttribute("comment") Comment comment, Principal principal) {
        Topic topic = topicService.findTopicById(id);
        User user = userService.findUserByFirstName(principal.getName());
        comment.setTopic(topic);
        comment.setUser(user);
        commentService.create(comment);
        return "redirect:/topic/topic/{id}";
    }

}
