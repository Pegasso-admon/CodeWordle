package com.crudzaso.codewordle.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents a single attempt made by a player in a game.
 */
@Getter
@Setter
@Builder
public class Attempt {
    private Long id;
    private Long gameId;
    private Integer attemptNumber;
    private String wordAttempt;
    private String feedback;
    private LocalDateTime createdAt;
}