package com.crudzaso.codewordle.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for attempt validation response.
 */
@Getter
@Setter
@Builder
public class AttemptResponseDTO {
    /**
     * Indicates if the attempt was valid.
     */
    private Boolean valid;
    
    /**
     * Visual feedback for the attempted word.
     */
    private String feedback;
    
    /**
     * Updated game status after the attempt.
     */
    private String gameStatus;
    
    /**
     * Number of attempts remaining.
     */
    private Integer remainingAttempts;
    
    /**
     * Indicates if the game was won with this attempt.
     */
    private Boolean won;
}
