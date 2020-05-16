package com.forum.service;


import com.forum.model.Theme;
import com.forum.model.User;
import com.forum.repository.ThemeRepository;
import com.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ThemeRepository themeRepository;


    public User getOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void delete(Long id) {
        Optional<User> toDelete = userRepository.findById(id);
        if (toDelete.isPresent()) {
            userRepository.delete(toDelete.get());
        }
    }

    public User create(User user) {
        return userRepository.save(user);
    }

//    private Set<Theme> resolveTheme(Set<Theme> themes) {
//        Set<Theme> resolved = new HashSet<>(themes.size());
//        for (Theme theme : themes) {
//            Theme g = themeRepository.findByName(theme.getName());
//            if (g == null) {
//                g = themeRepository.save(theme);
//            }
//            resolved.add(g);
//        }
//        return resolved;
//    }

}