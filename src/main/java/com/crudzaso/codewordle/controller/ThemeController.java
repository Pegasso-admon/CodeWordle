package com.crudzaso.codewordle.controller;

import com.crudzaso.codewordle.dto.ApiResponseDTO;
import com.crudzaso.codewordle.dto.ThemeDTO;
import com.crudzaso.codewordle.service.ThemeService;
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
 * REST controller for theme management operations.
 * Handles retrieval of available themes and theme details.
 */
@RestController
@RequestMapping("/api/themes")
@RequiredArgsConstructor
@Tag(name = "Theme Management", description = "APIs for managing game themes")
public class ThemeController {

    private final ThemeService themeService;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Retrieves all available themes.
     *
     * @return list of all themes with their details
     */
    @GetMapping
    @Operation(summary = "Get all themes", description = "Retrieves all available themes for game creation")
    public ResponseEntity<ApiResponseDTO<List<ThemeDTO>>> getAllThemes() {
        List<ThemeDTO> themes = themeService.getAllThemes();
        
        ApiResponseDTO<List<ThemeDTO>> response = ApiResponseDTO.<List<ThemeDTO>>builder()
                .success(true)
                .message("Themes retrieved successfully")
                .data(themes)
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
                
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves a theme by its ID.
     *
     * @param themeId the unique identifier of the theme
     * @return theme details including word count
     */
    @GetMapping("/{themeId}")
    @Operation(summary = "Get theme by ID", description = "Retrieves detailed information about a specific theme")
    public ResponseEntity<ApiResponseDTO<ThemeDTO>> getThemeById(@PathVariable Long themeId) {
        ThemeDTO theme = themeService.getThemeById(themeId);
        
        ApiResponseDTO<ThemeDTO> response = ApiResponseDTO.<ThemeDTO>builder()
                .success(true)
                .message("Theme retrieved successfully")
                .data(theme)
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
                
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}