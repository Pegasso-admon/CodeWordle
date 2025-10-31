package com.crudzaso.codewordle.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for creating a new game request.
 */
@Getter
@Setter
@Builder
public class GameRequestDTO {
    /**
     * ID of the theme selected for the game.
     */
    private Long themeId;
    
    /**
     * Maximum number of attempts allowed (optional, defaults to 6).
     */
    private Integer maxAttempts;
}