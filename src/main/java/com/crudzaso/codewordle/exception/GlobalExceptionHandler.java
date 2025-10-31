package com.crudzaso.codewordle.exception;

import com.crudzaso.codewordle.dto.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Global exception handler for REST controllers.
 * Handles all exceptions thrown by the application and returns standardized responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Handles GameNotFoundException and returns a 404 response.
     *
     * @param ex the GameNotFoundException instance
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleGameNotFound(GameNotFoundException ex) {
        ApiResponseDTO<Object> response = ApiResponseDTO.builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles ThemeNotFoundException and returns a 404 response.
     *
     * @param ex the ThemeNotFoundException instance
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(ThemeNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleThemeNotFound(ThemeNotFoundException ex) {
        ApiResponseDTO<Object> response = ApiResponseDTO.builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles InvalidGameStateException and returns a 400 response.
     *
     * @param ex the InvalidGameStateException instance
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(InvalidGameStateException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleInvalidGameState(InvalidGameStateException ex) {
        ApiResponseDTO<Object> response = ApiResponseDTO.builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles InvalidWordException and returns a 400 response.
     *
     * @param ex the InvalidWordException instance
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(InvalidWordException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleInvalidWord(InvalidWordException ex) {
        ApiResponseDTO<Object> response = ApiResponseDTO.builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles generic exceptions and returns a 500 response.
     *
     * @param ex the Exception instance
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleGenericException(Exception ex) {
        ApiResponseDTO<Object> response = ApiResponseDTO.builder()
                .success(false)
                .message("An unexpected error occurred: " + ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}