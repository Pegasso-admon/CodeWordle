package com.crudzaso.codewordle.service;

import com.crudzaso.codewordle.dto.ThemeDTO;

import java.util.List;

/**
 * Service interface for theme management operations.
 */
public interface ThemeService {
    
    /**
     * Retrieves all available themes.
     *
     * @return list of all themes with their details
     */
    List<ThemeDTO> getAllThemes();
    
    /**
     * Retrieves a theme by its ID.
     *
     * @param themeId the unique identifier of the theme
     * @return theme details
     */
    ThemeDTO getThemeById(Long themeId);
    
    /**
     * Retrieves a theme by its name.
     *
     * @param themeName the name of the theme
     * @return theme details
     */
    ThemeDTO getThemeByName(String themeName);
}