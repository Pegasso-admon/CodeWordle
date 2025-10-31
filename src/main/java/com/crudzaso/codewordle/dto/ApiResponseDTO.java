package com.crudzaso.codewordle.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Standard API response wrapper for consistent REST API responses.
 * @param <T> the type of data contained in the response
 */
@Getter
@Setter
@Builder
public class ApiResponseDTO<T> {
    /**
     * Indicates whether the request was successful.
     */
    private Boolean success;
    
    /**
     * Human-readable message describing the result.
     */
    private String message;
    
    /**
     * The actual data payload of the response.
     */
    private T data;
    
    /**
     * Timestamp when the response was generated.
     */
    private String timestamp;
}