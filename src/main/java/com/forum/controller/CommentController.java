package com.forum.controller;

import com.forum.model.Comment;
import com.forum.model.Topic;
import com.forum.model.User;
import com.forum.service.CommentService;
import com.forum.service.TopicService;
import com.forum.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final TopicService topicService;
    private final CommentService commentService;
    private final UserService userService;

    public CommentController(TopicService topicService, CommentService commentService, UserService userService) {
        this.topicService = topicService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String addComment(Model model, @PathVariable Long id, Principal principal) {
        Topic topic = topicService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Not found topic with id: " + id));
        User user = userService.findUserByFirstName(principal.getName());
        Comment comment = new Comment();
        comment.setTopic(topic);
        comment.setUser(user);
        model.addAttribute("comment", comment);
        return "commentAdd";
    }

    @PostMapping("/{id}")
    public String saveComment(@PathVariable Long id,
                              @ModelAttribute("comment") Comment comment, Principal principal) {
        Topic topic = topicService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Not found topic with id: " + id));
        User user = userService.findUserByFirstName(principal.getName());
        comment.setTopic(topic);
        comment.setUser(user);
        commentService.create(comment);
        return "redirect:/topic/{id}/forum";
    }

}
