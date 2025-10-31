package com.crudzaso.codewordle.exception;

/**
 * Exception thrown when an invalid word is submitted for a game attempt.
 */
public class InvalidWordException extends RuntimeException {
    
    /**
     * Constructs a new InvalidWordException with the specified word and reason.
     *
     * @param word the invalid word that was submitted
     * @param reason the reason why the word is invalid
     */
    public InvalidWordException(String word, String reason) {
        super("Word '" + word + "' is invalid: " + reason);
    }

    /**
     * Constructs a new InvalidWordException for a word not in the theme.
     *
     * @param word the word that was not found in the theme
     * @param themeName the name of the theme
     */
    public InvalidWordException(String word, String themeName, boolean notInTheme) {
        super("Word '" + word + "' is not available in theme: " + themeName);
    }

    /**
     * Constructs a new InvalidWordException with a custom message.
     *
     * @param message the detail message
     */
    public InvalidWordException(String message) {
        super(message);
    }
}