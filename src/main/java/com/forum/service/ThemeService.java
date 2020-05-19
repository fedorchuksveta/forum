package com.forum.service;

import com.forum.model.Theme;
import com.forum.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {

    private final ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }


    public Theme getOne(Long id) {
        return themeRepository.findById(id).orElse(null);
    }

    public List<Theme> findAll() {
        return themeRepository.findAll();
    }


    public void delete(Long id) {
        Optional<Theme> toDelete = themeRepository.findById(id);
        toDelete.ifPresent(themeRepository::delete);
    }

    public Theme update(Theme theme) {

        Optional<Theme> themeOp = themeRepository.findById(theme.getId());
        if (themeOp.isPresent()) {
            Theme newTheme = themeOp.get();
            newTheme.setName(theme.getName());
            newTheme.setDescription(theme.getDescription());
            newTheme = themeRepository.save(newTheme);
            return newTheme;
        } else {
            theme = themeRepository.save(theme);
            return theme;
        }
    }

    public Theme findThemeById(long id) {
        return themeRepository.findThemeById(id);
    }

    public Theme create(Theme theme) {
        return themeRepository.save(theme);
    }
}
