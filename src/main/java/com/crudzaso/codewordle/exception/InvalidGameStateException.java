package com.crudzaso.codewordle.exception;

/**
 * Exception thrown when an operation is attempted on a game in an invalid state.
 */
public class InvalidGameStateException extends RuntimeException {
    
    /**
     * Constructs a new InvalidGameStateException with the specified detail message.
     *
     * @param message the detail message explaining the invalid state
     */
    public InvalidGameStateException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidGameStateException for a completed game.
     *
     * @param gameId the ID of the completed game
     * @param status the current status of the game
     */
    public InvalidGameStateException(Long gameId, String status) {
        super("Game " + gameId + " is already " + status.toLowerCase() + " and cannot accept new attempts");
    }
}