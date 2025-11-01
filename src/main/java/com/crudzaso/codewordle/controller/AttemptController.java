package com.crudzaso.codewordle.controller;

import com.crudzaso.codewordle.dto.ApiResponseDTO;
import com.crudzaso.codewordle.dto.AttemptDTO;
import com.crudzaso.codewordle.dto.AttemptRequestDTO;
import com.crudzaso.codewordle.dto.AttemptResponseDTO;
import com.crudzaso.codewordle.service.AttemptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * REST controller for game attempt operations.
 * Handles word attempts, validation, and attempt history.
 */
@RestController
@RequestMapping("/api/attempts")
@RequiredArgsConstructor
@Tag(name = "Attempt Management", description = "APIs for managing game attempts")
public class AttemptController {

    private final AttemptService attemptService;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Processes a word attempt for a specific game.
     *
     * @param attemptRequest the attempt request containing game ID and word
     * @return response with validation results and game state
     */
    @PostMapping
    @Operation(summary = "Submit a word attempt", description = "Processes a word attempt and returns feedback")
    public ResponseEntity<ApiResponseDTO<AttemptResponseDTO>> submitAttempt(@RequestBody AttemptRequestDTO attemptRequest) {
        AttemptResponseDTO attemptResponse = attemptService.processAttempt(attemptRequest);
        
        ApiResponseDTO<AttemptResponseDTO> response = ApiResponseDTO.<AttemptResponseDTO>builder()
                .success(true)
                .message("Attempt processed successfully")
                .data(attemptResponse)
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
                
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves all attempts for a specific game.
     *
     * @param gameId the unique identifier of the game
     * @return list of attempts made in the game
     */
    @GetMapping("/game/{gameId}")
    @Operation(summary = "Get attempts by game", description = "Retrieves all attempts for a specific game")
    public ResponseEntity<ApiResponseDTO<List<AttemptDTO>>> getAttemptsByGame(@PathVariable Long gameId) {
        List<AttemptDTO> attempts = attemptService.getAttemptsByGameId(gameId);
        
        ApiResponseDTO<List<AttemptDTO>> response = ApiResponseDTO.<List<AttemptDTO>>builder()
                .success(true)
                .message("Attempts retrieved successfully")
                .data(attempts)
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
                
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}