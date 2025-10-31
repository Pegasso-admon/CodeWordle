package com.crudzaso.codewordle.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.crudzaso.codewordle.model.Attempt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Repository for managing Attempt entities in the database.
 * Provides CRUD operations for game attempts.
 */
@Repository
@RequiredArgsConstructor
public class AttemptRepository {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Saves a new attempt to the database.
     *
     * @param attempt the Attempt object to be saved
     */
    public void save(Attempt attempt) {
        String sql = "INSERT INTO attempts (game_id, attempt_number, word_attempt, feedback, created_at) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, attempt.getGameId(), attempt.getAttemptNumber(),
                attempt.getWordAttempt(), attempt.getFeedback(), attempt.getCreatedAt());
    }

    /**
     * Finds all attempts for a specific game.
     *
     * @param gameId the ID of the game
     * @return list of Attempt objects for the specified game
     */
    public List<Attempt> findByGameId(Long gameId) {
        String sql = "SELECT * FROM attempts WHERE game_id = ? ORDER BY attempt_number";
        return jdbcTemplate.query(sql, new AttemptRowMapper(), gameId);
    }

    /**
     * Counts the number of attempts made in a specific game.
     *
     * @param gameId the ID of the game
     * @return the number of attempts for the specified game
     */
    public int countAttemptsByGameId(Long gameId) {
        String sql = "SELECT COUNT(*) FROM attempts WHERE game_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, gameId);
    }

    /**
     * RowMapper implementation for mapping ResultSet rows to Attempt objects.
     */
    private static class AttemptRowMapper implements RowMapper<Attempt> {
        @Override
        public Attempt mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Attempt.builder()
                    .id(rs.getLong("id"))
                    .gameId(rs.getLong("game_id"))
                    .attemptNumber(rs.getInt("attempt_number"))
                    .wordAttempt(rs.getString("word_attempt"))
                    .feedback(rs.getString("feedback"))
                    .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                    .build();
        }
    }
}