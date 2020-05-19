package com.forum.service;

import com.forum.model.Comment;
import com.forum.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAllByTopic(long id) {
        return commentRepository.findByTopicId(id);
    }

    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }
}
