package com.crudzaso.codewordle.service;

import com.crudzaso.codewordle.dto.ThemeDTO;
import com.crudzaso.codewordle.exception.ThemeNotFoundException;
import com.crudzaso.codewordle.repository.ThemeRepository;
import com.crudzaso.codewordle.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for theme management operations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {
    
    private final ThemeRepository themeRepository;
    private final WordRepository wordRepository;
    
    @Override
    public List<ThemeDTO> getAllThemes() {
        log.debug("Retrieving all themes");
        
        return themeRepository.findAll().stream()
                .map(theme -> {
                    int wordCount = wordRepository.findByThemeId(theme.getId()).size();
                    return ThemeDTO.builder()
                            .id(theme.getId())
                            .name(theme.getName())
                            .description(theme.getDescription())
                            .wordCount(wordCount)
                            .build();
                })
                .collect(Collectors.toList());
    }
    
    @Override
    public ThemeDTO getThemeById(Long themeId) {
        log.debug("Retrieving theme by ID: {}", themeId);
        
        return themeRepository.findById(themeId)
                .map(theme -> {
                    int wordCount = wordRepository.findByThemeId(theme.getId()).size();
                    return ThemeDTO.builder()
                            .id(theme.getId())
                            .name(theme.getName())
                            .description(theme.getDescription())
                            .wordCount(wordCount)
                            .build();
                })
                .orElseThrow(() -> new ThemeNotFoundException(themeId));
    }
    
    @Override
    public ThemeDTO getThemeByName(String themeName) {
        log.debug("Retrieving theme by name: {}", themeName);
        
        return themeRepository.findByName(themeName)
                .map(theme -> {
                    int wordCount = wordRepository.findByThemeId(theme.getId()).size();
                    return ThemeDTO.builder()
                            .id(theme.getId())
                            .name(theme.getName())
                            .description(theme.getDescription())
                            .wordCount(wordCount)
                            .build();
                })
                .orElseThrow(() -> new ThemeNotFoundException(themeName));
    }
}