package com.forum.service;

import com.forum.model.User;
import com.forum.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User findUserByFirstName(String name) {
        return userRepository.findUserByFirstName(name);
    }

    public Optional<User> findUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        return user;
    }
}