package com.crudzaso.codewordle.exception;

/**
 * Exception thrown when a requested game is not found.
 */
public class GameNotFoundException extends RuntimeException {
    
    /**
     * Constructs a new GameNotFoundException with the specified game ID.
     *
     * @param gameId the ID of the game that was not found
     */
    public GameNotFoundException(Long gameId) {
        super("Game not found with ID: " + gameId);
    }

    /**
     * Constructs a new GameNotFoundException with a custom message.
     *
     * @param message the detail message
     */
    public GameNotFoundException(String message) {
        super(message);
    }
}