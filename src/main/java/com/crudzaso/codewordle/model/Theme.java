package com.crudzaso.codewordle.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a theme category for grouping words.
 */
@Getter
@Setter
@Builder
public class Theme {
    private Long id;
    private String name;
    private String description;
}