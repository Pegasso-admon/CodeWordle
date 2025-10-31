package com.crudzaso.codewordle.exception;

/**
 * Exception thrown when a requested theme is not found.
 */
public class ThemeNotFoundException extends RuntimeException {
    
    /**
     * Constructs a new ThemeNotFoundException with the specified theme ID.
     *
     * @param themeId the ID of the theme that was not found
     */
    public ThemeNotFoundException(Long themeId) {
        super("Theme not found with ID: " + themeId);
    }

    /**
     * Constructs a new ThemeNotFoundException with the specified theme name.
     *
     * @param themeName the name of the theme that was not found
     */
    public ThemeNotFoundException(String themeName) {
        super("Theme not found with name: " + themeName);
    }
}