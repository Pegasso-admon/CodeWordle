package com.crudzaso.codewordle.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for game creation response.
 */
@Getter
@Setter
@Builder
public class GameResponseDTO {
    /**
     * Unique identifier of the created game.
     */
    private Long gameId;
    
    /**
     * Descriptive message about the game status.
     */
    private String message;
    
    /**
     * Current status of the game.
     */
    private String status;
    
    /**
     * Number of remaining attempts.
     */
    private Integer remainingAttempts;
}
