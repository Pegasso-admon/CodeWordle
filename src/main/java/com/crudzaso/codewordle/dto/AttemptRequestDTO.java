package com.crudzaso.codewordle.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for submitting a new attempt.
 */
@Getter
@Setter
@Builder
public class AttemptRequestDTO {
    /**
     * ID of the game where the attempt is being made.
     */
    private Long gameId;
    
    /**
     * The word being attempted by the player.
     */
    private String word;
}
