package com.crudzaso.codewordle.controller;

import com.crudzaso.codewordle.dto.ApiResponseDTO;
import com.crudzaso.codewordle.dto.WordDTO;
import com.crudzaso.codewordle.dto.WordValidationDTO;
import com.crudzaso.codewordle.service.WordService;
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
 * REST controller for word management operations.
 * Handles word retrieval and validation.
 */
@RestController
@RequestMapping("/api/words")
@RequiredArgsConstructor
@Tag(name = "Word Management", description = "APIs for managing and validating words")
public class WordController {

    private final WordService wordService;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Retrieves all words for a specific theme.
     *
     * @param themeId the unique identifier of the theme
     * @return list of words belonging to the theme
     */
    @GetMapping("/theme/{themeId}")
    @Operation(summary = "Get words by theme", description = "Retrieves all words available for a specific theme")
    public ResponseEntity<ApiResponseDTO<List<WordDTO>>> getWordsByTheme(@PathVariable Long themeId) {
        List<WordDTO> words = wordService.getWordsByThemeId(themeId);
        
        ApiResponseDTO<List<WordDTO>> response = ApiResponseDTO.<List<WordDTO>>builder()
                .success(true)
                .message("Words retrieved successfully")
                .data(words)
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
                
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Validates if a word exists in a specific theme.
     *
     * @param word the word to validate
     * @param themeId the theme ID to check against
     * @return validation result with details
     */
    @GetMapping("/validate")
    @Operation(summary = "Validate word", description = "Validates if a word exists in the specified theme")
    public ResponseEntity<ApiResponseDTO<WordValidationDTO>> validateWord(
            @RequestParam String word, 
            @RequestParam Long themeId) {
        WordValidationDTO validation = wordService.validateWord(word, themeId);
        
        ApiResponseDTO<WordValidationDTO> response = ApiResponseDTO.<WordValidationDTO>builder()
                .success(true)
                .message("Word validation completed")
                .data(validation)
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
                
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves all available words across all themes.
     *
     * @return list of all words in the system
     */
    @GetMapping
    @Operation(summary = "Get all words", description = "Retrieves all words available in the system")
    public ResponseEntity<ApiResponseDTO<List<WordDTO>>> getAllWords() {
        List<WordDTO> words = wordService.getAllWords();
        
        ApiResponseDTO<List<WordDTO>> response = ApiResponseDTO.<List<WordDTO>>builder()
                .success(true)
                .message("All words retrieved successfully")
                .data(words)
                .timestamp(LocalDateTime.now().format(formatter))
                .build();
                
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}