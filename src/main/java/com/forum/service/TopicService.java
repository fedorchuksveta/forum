package com.forum.service;

import com.forum.model.Topic;
import com.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    public List<Topic> findAllByTheme(long id) {
        return topicRepository.findByThemeId(id);
    }

    public Optional<Topic> findById(long id) {
        return topicRepository.findById(id);
    }

    public Topic create(Topic topic) {
        return topicRepository.save(topic);
    }
}