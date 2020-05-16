package com.forum.repository;


import com.forum.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
}

