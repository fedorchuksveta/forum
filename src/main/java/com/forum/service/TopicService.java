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

    public Topic getOne(Long id) {
        return topicRepository.findById(id).orElse(null);
    }

    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    public void delete(Long id) {
        Optional<Topic> toDelete = topicRepository.findById(id);
        if (toDelete.isPresent()) {
            topicRepository.delete(toDelete.get());
        }
    }

    public Optional<Topic> findById(long id) {
        return topicRepository.findById(id);
    }

    public Topic create(Topic topic) {
        return topicRepository.save(topic);
    }
}