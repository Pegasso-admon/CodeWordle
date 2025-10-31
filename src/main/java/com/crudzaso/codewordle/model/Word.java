package com.crudzaso.codewordle.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a word that can be used as a target word in games.
 */
@Getter
@Setter
@Builder
public class Word {
    private Long id;
    private String word;
    private Long themeId;
}