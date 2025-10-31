package com.crudzaso.codewordle.service;

import com.crudzaso.codewordle.dto.GameDTO;
import com.crudzaso.codewordle.dto.GameRequestDTO;
import com.crudzaso.codewordle.dto.GameResponseDTO;
import com.crudzaso.codewordle.model.Game;
import com.crudzaso.codewordle.model.GameStatus;

/**
 * Service interface for game management operations.
 */
public interface GameService {
    
    /**
     * Creates a new game with the specified theme and maximum attempts.
     *
     * @param gameRequest the game creation request containing theme and max attempts
     * @return response with game ID and initial status
     */
    GameResponseDTO createGame(GameRequestDTO gameRequest);
    
    /**
     * Retrieves game information by its ID.
     *
     * @param gameId the unique identifier of the game
     * @return complete game information
     */
    GameDTO getGameById(Long gameId);
    
    /**
     * Retrieves the current active game.
     *
     * @return the current active game, or null if none exists
     */
    GameDTO getCurrentGame();
    
    /**
     * Updates the status of a game.
     *
     * @param gameId the ID of the game to update
     * @param status the new status to set
     */
    void updateGameStatus(Long gameId, GameStatus status);
    
    /**
     * Retrieves the raw game entity by ID for internal operations.
     *
     * @param gameId the unique identifier of the game
     * @return the Game entity
     */
    Game getGameEntity(Long gameId);
}