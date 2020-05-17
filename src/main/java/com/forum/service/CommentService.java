package com.forum.service;

import com.forum.model.Comment;
import com.forum.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> findAllByTopic(long id) {
        return commentRepository.findByTopicId(id);
    }

    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }
}
