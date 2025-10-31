package com.crudzaso.codewordle.repository;

import com.crudzaso.codewordle.model.Game;
import com.crudzaso.codewordle.model.GameStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Repository for managing Game entities in the database.
 * Provides operations for game lifecycle management.
 */
@Repository
@RequiredArgsConstructor
public class GameRepository {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Saves a new game to the database.
     *
     * @param game the Game object to be saved
     */
    public void save(Game game) {
        String sql = "INSERT INTO games (target_word, theme_id, status, max_attempts, created_at) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, game.getTargetWord(), game.getThemeId(),
                game.getStatus().name(), game.getMaxAttempts(), game.getCreatedAt());
    }

    /**
     * Updates an existing game in the database.
     *
     * @param game the Game object with updated information
     */
    public void update(Game game) {
        String sql = "UPDATE games SET status = ?, completed_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, game.getStatus().name(), game.getCompletedAt(), game.getId());
    }

    /**
     * Finds a game by its ID.
     *
     * @param id the ID of the game to find
     * @return an Optional containing the Game if found, empty otherwise
     */
    public Optional<Game> findById(Long id) {
        String sql = "SELECT * FROM games WHERE id = ?";
        return jdbcTemplate.query(sql, new GameRowMapper(), id)
                .stream()
                .findFirst();
    }

    /**
     * Finds the most recent active game.
     *
     * @return an Optional containing the latest active Game if found, empty
     *         otherwise
     */
    public Optional<Game> findLatestActiveGame() {
        String sql = "SELECT * FROM games WHERE status = 'IN_PROGRESS' ORDER BY created_at DESC LIMIT 1";
        return jdbcTemplate.query(sql, new GameRowMapper())
                .stream()
                .findFirst();
    }

    /**
     * RowMapper implementation for mapping ResultSet rows to Game objects.
     */
    private static class GameRowMapper implements RowMapper<Game> {
        @Override
        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Game.builder()
                    .id(rs.getLong("id"))
                    .targetWord(rs.getString("target_word"))
                    .themeId(rs.getLong("theme_id"))
                    .status(GameStatus.valueOf(rs.getString("status")))
                    .maxAttempts(rs.getInt("max_attempts"))
                    .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                    .completedAt(
                            rs.getTimestamp("completed_at") != null ? rs.getTimestamp("completed_at").toLocalDateTime()
                                    : null)
                    .build();
        }
    }
}