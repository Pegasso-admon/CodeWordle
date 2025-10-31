package com.crudzaso.codewordle.service;

import com.crudzaso.codewordle.dto.GameDTO;
import com.crudzaso.codewordle.dto.GameRequestDTO;
import com.crudzaso.codewordle.dto.GameResponseDTO;
import com.crudzaso.codewordle.exception.GameNotFoundException;
import com.crudzaso.codewordle.exception.ThemeNotFoundException;
import com.crudzaso.codewordle.model.Game;
import com.crudzaso.codewordle.model.GameStatus;
import com.crudzaso.codewordle.repository.GameRepository;
import com.crudzaso.codewordle.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service implementation for game management operations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    
    private final GameRepository gameRepository;
    private final ThemeRepository themeRepository;
    private final WordService wordService;
    
    private static final Integer DEFAULT_MAX_ATTEMPTS = 6;
    
    @Override
    @Transactional
    public GameResponseDTO createGame(GameRequestDTO gameRequest) {
        log.info("Creating new game for theme ID: {}", gameRequest.getThemeId());
        
        validateThemeExists(gameRequest.getThemeId());
        
        String targetWord = wordService.getRandomWordByThemeId(gameRequest.getThemeId());
        Integer maxAttempts = Optional.ofNullable(gameRequest.getMaxAttempts())
                .orElse(DEFAULT_MAX_ATTEMPTS);
        
        Game game = Game.builder()
                .targetWord(targetWord)
                .themeId(gameRequest.getThemeId())
                .status(GameStatus.IN_PROGRESS)
                .maxAttempts(maxAttempts)
                .createdAt(LocalDateTime.now())
                .build();
        
        gameRepository.save(game);
        log.debug("Game created with ID: {} and target word: {}", game.getId(), targetWord);
        
        return GameResponseDTO.builder()
                .gameId(game.getId())
                .message("Game created successfully")
                .status(game.getStatus().name())
                .remainingAttempts(maxAttempts)
                .build();
    }
    
    @Override
    public GameDTO getGameById(Long gameId) {
        log.debug("Retrieving game with ID: {}", gameId);
        
        Game game = getGameEntity(gameId);
        String themeName = getThemeName(game.getThemeId());
        
        return GameDTO.builder()
                .id(game.getId())
                .targetWord(game.getTargetWord())
                .themeName(themeName)
                .status(game.getStatus().name())
                .maxAttempts(game.getMaxAttempts())
                .currentAttempt(0) // This should be calculated from attempts
                .createdAt(game.getCreatedAt())
                .build();
    }
    
    @Override
    public GameDTO getCurrentGame() {
        log.debug("Retrieving current active game");
        
        Optional<Game> currentGame = gameRepository.findLatestActiveGame();
        if (currentGame.isPresent()) {
            Game game = currentGame.get();
            String themeName = getThemeName(game.getThemeId());
            
            return GameDTO.builder()
                    .id(game.getId())
                    .targetWord(game.getTargetWord())
                    .themeName(themeName)
                    .status(game.getStatus().name())
                    .maxAttempts(game.getMaxAttempts())
                    .currentAttempt(0) // This should be calculated from attempts
                    .createdAt(game.getCreatedAt())
                    .build();
        }
        
        return null;
    }
    
    @Override
    @Transactional
    public void updateGameStatus(Long gameId, GameStatus status) {
        log.info("Updating game {} status to {}", gameId, status);
        
        Game game = getGameEntity(gameId);
        game.setStatus(status);
        
        if (status == GameStatus.WON || status == GameStatus.LOST) {
            game.setCompletedAt(LocalDateTime.now());
        }
        
        gameRepository.update(game);
    }
    
    @Override
    public Game getGameEntity(Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));
    }
    
    private void validateThemeExists(Long themeId) {
        if (!themeRepository.findById(themeId).isPresent()) {
            throw new ThemeNotFoundException(themeId);
        }
    }
    
    private String getThemeName(Long themeId) {
        return themeRepository.findById(themeId)
                .map(theme -> theme.getName())
                .orElse("Unknown Theme");
    }
}