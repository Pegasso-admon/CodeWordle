package com.crudzaso.codewordle.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents a game session in the CodeWordle application.
 */
@Getter
@Setter
@Builder
public class Game {
    private Long id;
    private String targetWord;
    private Long themeId;
    private GameStatus status;
    private Integer maxAttempts;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
}