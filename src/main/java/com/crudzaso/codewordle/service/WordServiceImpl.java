package com.crudzaso.codewordle.service;

import com.crudzaso.codewordle.dto.WordDTO;
import com.crudzaso.codewordle.dto.WordValidationDTO;
import com.crudzaso.codewordle.exception.ThemeNotFoundException;
import com.crudzaso.codewordle.model.Word;
import com.crudzaso.codewordle.repository.ThemeRepository;
import com.crudzaso.codewordle.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for word management operations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {
    
    private final WordRepository wordRepository;
    private final ThemeRepository themeRepository;
    
    @Override
    public List<WordDTO> getWordsByThemeId(Long themeId) {
        log.debug("Retrieving words for theme ID: {}", themeId);
        
        validateThemeExists(themeId);
        
        return wordRepository.findByThemeId(themeId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public String getRandomWordByThemeId(Long themeId) {
        log.debug("Selecting random word for theme ID: {}", themeId);
        
        validateThemeExists(themeId);
        
        return wordRepository.findRandomWordByThemeId(themeId)
                .map(Word::getWord)
                .orElseThrow(() -> new RuntimeException("No words found for theme ID: " + themeId));
    }
    
    @Override
    public WordValidationDTO validateWord(String word, Long themeId) {
        log.debug("Validating word '{}' for theme ID: {}", word, themeId);
        
        boolean isValid = wordRepository.existsByWordAndThemeId(word.toLowerCase(), themeId);
        String message = isValid ? "Word is valid" : "Word not found in theme";
        
        return WordValidationDTO.builder()
                .valid(isValid)
                .message(message)
                .length(word.length())
                .build();
    }
    
    @Override
    public List<WordDTO> getAllWords() {
        log.debug("Retrieving all words");
        
        return wordRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    private void validateThemeExists(Long themeId) {
        if (!themeRepository.findById(themeId).isPresent()) {
            throw new ThemeNotFoundException(themeId);
        }
    }
    
    private WordDTO convertToDTO(Word word) {
        String themeName = themeRepository.findById(word.getThemeId())
                .map(theme -> theme.getName())
                .orElse("Unknown Theme");
        
        return WordDTO.builder()
                .id(word.getId())
                .word(word.getWord())
                .themeName(themeName)
                .build();
    }
}