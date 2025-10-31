package com.crudzaso.codewordle.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for word information display.
 */
@Getter
@Setter
@Builder
public class WordDTO {
    /**
     * Unique identifier of the word.
     */
    private Long id;
    
    /**
     * The actual word text.
     */
    private String word;
    
    /**
     * Name of the theme this word belongs to.
     */
    private String themeName;
}

