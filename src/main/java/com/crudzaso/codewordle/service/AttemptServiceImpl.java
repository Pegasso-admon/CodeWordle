package com.crudzaso.codewordle.service;

import com.crudzaso.codewordle.dto.AttemptDTO;
import com.crudzaso.codewordle.dto.AttemptRequestDTO;
import com.crudzaso.codewordle.dto.AttemptResponseDTO;
import com.crudzaso.codewordle.exception.GameNotFoundException;
import com.crudzaso.codewordle.exception.InvalidGameStateException;
import com.crudzaso.codewordle.exception.InvalidWordException;
import com.crudzaso.codewordle.exception.MaxAttemptsExceededException;
import com.crudzaso.codewordle.model.Attempt;
import com.crudzaso.codewordle.model.Game;
import com.crudzaso.codewordle.model.GameStatus;
import com.crudzaso.codewordle.repository.AttemptRepository;
import com.crudzaso.codewordle.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for game attempt operations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AttemptServiceImpl implements AttemptService {
    
    private final AttemptRepository attemptRepository;
    private final GameRepository gameRepository;
    private final WordService wordService;
    private final GameService gameService;
    
    @Override
    @Transactional
    public AttemptResponseDTO processAttempt(AttemptRequestDTO attemptRequest) {
        log.info("Processing attempt for game ID: {}", attemptRequest.getGameId());
        
        Game game = gameService.getGameEntity(attemptRequest.getGameId());
        validateGameState(game);
        
        String word = attemptRequest.getWord().toLowerCase().trim();
        validateWord(word, game.getThemeId());
        
        int attemptNumber = attemptRepository.countAttemptsByGameId(game.getId()) + 1;
        validateMaxAttempts(attemptNumber, game.getMaxAttempts(), game.getId());
        
        String feedback = generateFeedback(word, game.getTargetWord());
        boolean isCorrect = word.equals(game.getTargetWord());
        
        saveAttempt(game.getId(), attemptNumber, word, feedback);
        
        GameStatus newStatus = determineGameStatus(isCorrect, attemptNumber, game.getMaxAttempts());
        if (newStatus != game.getStatus()) {
            gameService.updateGameStatus(game.getId(), newStatus);
        }
        
        log.debug("Attempt processed - Game: {}, Attempt: {}, Correct: {}", 
                 game.getId(), attemptNumber, isCorrect);
        
        return AttemptResponseDTO.builder()
                .valid(true)
                .feedback(feedback)
                .gameStatus(newStatus.name())
                .remainingAttempts(game.getMaxAttempts() - attemptNumber)
                .won(isCorrect)
                .build();
    }
    
    @Override
    public List<AttemptDTO> getAttemptsByGameId(Long gameId) {
        log.debug("Retrieving attempts for game ID: {}", gameId);
        
        return attemptRepository.findByGameId(gameId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean isValidWordForTheme(String word, Long themeId) {
        return wordService.validateWord(word, themeId).getValid();
    }
    
    @Override
    public String generateFeedback(String attempt, String targetWord) {
        StringBuilder feedback = new StringBuilder();
        char[] attemptChars = attempt.toCharArray();
        char[] targetChars = targetWord.toCharArray();
        
        for (int i = 0; i < attemptChars.length; i++) {
            if (attemptChars[i] == targetChars[i]) {
                feedback.append("🟩"); // Correct position
            } else if (targetWord.contains(String.valueOf(attemptChars[i]))) {
                feedback.append("🟨"); // Correct letter, wrong position
            } else {
                feedback.append("⬜"); // Incorrect letter
            }
        }
        
        return feedback.toString();
    }
    
    private void validateGameState(Game game) {
        if (game.getStatus() != GameStatus.IN_PROGRESS) {
            throw new InvalidGameStateException(game.getId(), game.getStatus().name());
        }
    }
    
    private void validateWord(String word, Long themeId) {
        if (!isValidWordForTheme(word, themeId)) {
            throw new InvalidWordException(word, "Word not found in theme");
        }
    }
    
    private void validateMaxAttempts(int attemptNumber, int maxAttempts, Long gameId) {
        if (attemptNumber > maxAttempts) {
            throw new MaxAttemptsExceededException(gameId, maxAttempts);
        }
    }
    
    private void saveAttempt(Long gameId, int attemptNumber, String word, String feedback) {
        Attempt attempt = Attempt.builder()
                .gameId(gameId)
                .attemptNumber(attemptNumber)
                .wordAttempt(word)
                .feedback(feedback)
                .createdAt(LocalDateTime.now())
                .build();
        
        attemptRepository.save(attempt);
    }
    
    private GameStatus determineGameStatus(boolean isCorrect, int attemptNumber, int maxAttempts) {
        if (isCorrect) {
            return GameStatus.WON;
        } else if (attemptNumber >= maxAttempts) {
            return GameStatus.LOST;
        }
        return GameStatus.IN_PROGRESS;
    }
    
    private AttemptDTO convertToDTO(Attempt attempt) {
        return AttemptDTO.builder()
                .id(attempt.getId())
                .attemptNumber(attempt.getAttemptNumber())
                .wordAttempt(attempt.getWordAttempt())
                .feedback(attempt.getFeedback())
                .createdAt(attempt.getCreatedAt())
                .build();
    }
}