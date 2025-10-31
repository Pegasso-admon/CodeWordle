package com.crudzaso.codewordle.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for game information display.
 */
@Getter
@Setter
@Builder
public class GameDTO {
    /**
     * Unique identifier of the game.
     */
    private Long id;
    
    /**
     * The target word to be guessed.
     */
    private String targetWord;
    
    /**
     * Name of the theme associated with the game.
     */
    private String themeName;
    
    /**
     * Current status of the game (IN_PROGRESS, WON, LOST).
     */
    private String status;
    
    /**
     * Maximum number of attempts allowed.
     */
    private Integer maxAttempts;
    
    /**
     * Current attempt number.
     */
    private Integer currentAttempt;
    
    /**
     * Date and time when the game was created.
     */
    private LocalDateTime createdAt;
}

