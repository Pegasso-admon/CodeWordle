package com.crudzaso.codewordle.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for word validation results.
 */
@Getter
@Setter
@Builder
public class WordValidationDTO {
    /**
     * Indicates if the word is valid for the current theme.
     */
    private Boolean valid;
    
    /**
     * Validation message explaining the result.
     */
    private String message;
    
    /**
     * Length of the validated word.
     */
    private Integer length;
}
