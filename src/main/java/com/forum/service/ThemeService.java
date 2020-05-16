package com.forum.service;


import com.forum.model.Theme;
import com.forum.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {


    @Autowired
    ThemeRepository themeRepository;


    public Theme getOne(Long id) {
        return themeRepository.findById(id).orElse(null);
    }

    public List<Theme> findAll() {
        return themeRepository.findAll();
    }

    public void delete(Long id) {
        Optional<Theme> toDelete = themeRepository.findById(id);
        if (toDelete.isPresent()) {
            themeRepository.delete(toDelete.get());
        }
    }

    public Theme create(Theme theme) {
        return themeRepository.save(theme);
    }
}
