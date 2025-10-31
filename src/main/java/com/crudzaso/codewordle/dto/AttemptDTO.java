package com.crudzaso.codewordle.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for attempt information display.
 */
@Getter
@Setter
@Builder
public class AttemptDTO {
    /**
     * Unique identifier of the attempt.
     */
    private Long id;
    
    /**
     * Sequential number of the attempt.
     */
    private Integer attemptNumber;
    
    /**
     * The word submitted by the player.
     */
    private String wordAttempt;
    
    /**
     * Feedback indicating letter matches and positions.
     */
    private String feedback;
    
    /**
     * Date and time when the attempt was made.
     */
    private LocalDateTime createdAt;
}

