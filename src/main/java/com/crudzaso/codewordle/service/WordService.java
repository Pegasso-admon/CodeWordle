package com.crudzaso.codewordle.service;

import com.crudzaso.codewordle.dto.WordDTO;
import com.crudzaso.codewordle.dto.WordValidationDTO;

import java.util.List;

/**
 * Service interface for word management operations.
 */
public interface WordService {
    
    /**
     * Retrieves all words for a specific theme.
     *
     * @param themeId the unique identifier of the theme
     * @return list of words belonging to the theme
     */
    List<WordDTO> getWordsByThemeId(Long themeId);
    
    /**
     * Selects a random word from a specific theme.
     *
     * @param themeId the unique identifier of the theme
     * @return a randomly selected word from the theme
     */
    String getRandomWordByThemeId(Long themeId);
    
    /**
     * Validates if a word exists in a specific theme.
     *
     * @param word the word to validate
     * @param themeId the theme ID to check against
     * @return validation result with details
     */
    WordValidationDTO validateWord(String word, Long themeId);
    
    /**
     * Retrieves all available words across all themes.
     *
     * @return list of all words in the system
     */
    List<WordDTO> getAllWords();
}