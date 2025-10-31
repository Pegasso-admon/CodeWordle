package com.crudzaso.codewordle.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for theme information display.
 */
@Getter
@Setter
@Builder
public class ThemeDTO {
    /**
     * Unique identifier of the theme.
     */
    private Long id;
    
    /**
     * Name of the theme.
     */
    private String name;
    
    /**
     * Description of the theme category.
     */
    private String description;
    
    /**
     * Number of words available in this theme.
     */
    private Integer wordCount;
}