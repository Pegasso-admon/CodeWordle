package com.crudzaso.codewordle.exception;

/**
 * Exception thrown when the maximum number of attempts is exceeded for a game.
 */
public class MaxAttemptsExceededException extends RuntimeException {
    
    /**
     * Constructs a new MaxAttemptsExceededException for the specified game.
     *
     * @param gameId the ID of the game
     * @param maxAttempts the maximum number of attempts allowed
     */
    public MaxAttemptsExceededException(Long gameId, Integer maxAttempts) {
        super("Game " + gameId + " has reached the maximum number of attempts: " + maxAttempts);
    }
}