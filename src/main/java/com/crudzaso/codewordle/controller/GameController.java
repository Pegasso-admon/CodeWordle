package com.crudzaso.codewordle.controller;

import com.crudzaso.codewordle.dto.ApiResponseDTO;
import com.crudzaso.codewordle.dto.GameDTO;
import com.crudzaso.codewordle.dto.GameRequestDTO;
import com.crudzaso.codewordle.dto.GameResponseDTO;
import com.crudzaso.codewordle.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * REST controller for game management operations.
 * Handles game creation, retrieval, and status updates.
 */
@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
@Tag(name = "Game Management", description = "APIs for managing CodeWordle games")
public class GameController {

    private final GameService gameService;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Creates a new game with the specified theme and maximum attempts.
     *
     * @param gameRequest the game creation request
     * @return response containing game ID and initial status
     */
    @PostMapping
    @Operation(summary = "Create a new game", description = "Creates a new CodeWordle game with the specified theme")
    public ResponseEntity<ApiResponseDTO<GameResponseDTO>> createGame(@RequestBody GameRequestDTO gameRequest) {
        GameResponseDTO gameResponse = gameService.createGame(gameRequest);
        
        ApiResponseDTO<GameResponseDTO> response = ApiResponseDTO.<GameResponseDTO>builder()
                .success(true)
                .message("Game created successfully")
                .data(gameResponse)
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
                
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves game information by its ID.
     *
     * @param gameId the unique identifier of the game
     * @return game details including target word and status
     */
    @GetMapping("/{gameId}")
    @Operation(summary = "Get game by ID", description = "Retrieves detailed information about a specific game")
    public ResponseEntity<ApiResponseDTO<GameDTO>> getGame(@PathVariable Long gameId) {
        GameDTO game = gameService.getGameById(gameId);
        
        ApiResponseDTO<GameDTO> response = ApiResponseDTO.<GameDTO>builder()
                .success(true)
                .message("Game retrieved successfully")
                .data(game)
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
                
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves the current active game.
     *
     * @return the current active game or null if none exists
     */
    @GetMapping("/current")
    @Operation(summary = "Get current game", description = "Retrieves the currently active game")
    public ResponseEntity<ApiResponseDTO<GameDTO>> getCurrentGame() {
        GameDTO currentGame = gameService.getCurrentGame();
        
        ApiResponseDTO<GameDTO> response = ApiResponseDTO.<GameDTO>builder()
                .success(true)
                .message(currentGame != null ? "Current game retrieved" : "No active game found")
                .data(currentGame)
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
                
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}