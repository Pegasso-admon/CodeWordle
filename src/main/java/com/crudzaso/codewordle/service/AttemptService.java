package com.crudzaso.codewordle.service;

import com.crudzaso.codewordle.dto.AttemptDTO;
import com.crudzaso.codewordle.dto.AttemptRequestDTO;
import com.crudzaso.codewordle.dto.AttemptResponseDTO;

import java.util.List;

/**
 * Service interface for game attempt operations.
 */
public interface AttemptService {
    
    /**
     * Processes a word attempt for a specific game.
     *
     * @param attemptRequest the attempt request containing game ID and word
     * @return response with validation results and game state
     */
    AttemptResponseDTO processAttempt(AttemptRequestDTO attemptRequest);
    
    /**
     * Retrieves all attempts for a specific game.
     *
     * @param gameId the unique identifier of the game
     * @return list of attempts made in the game
     */
    List<AttemptDTO> getAttemptsByGameId(Long gameId);
    
    /**
     * Validates if a word is acceptable for the current game theme.
     *
     * @param word the word to validate
     * @param themeId the theme ID to validate against
     * @return true if the word is valid for the theme, false otherwise
     */
    boolean isValidWordForTheme(String word, Long themeId);
    
    /**
     * Generates feedback for a word attempt compared to the target word.
     *
     * @param attempt the attempted word
     * @param targetWord the target word to compare against
     * @return string representing letter matches and positions
     */
    String generateFeedback(String attempt, String targetWord);
}