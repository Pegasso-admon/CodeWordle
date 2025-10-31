package com.crudzaso.codewordle.repository;

import com.crudzaso.codewordle.model.Word;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Repository for managing Word entities in the database.
 * Provides operations for retrieving word information by themes.
 */
@Repository
@RequiredArgsConstructor
public class WordRepository {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Finds all words belonging to a specific theme.
     *
     * @param themeId the ID of the theme
     * @return list of Word objects for the specified theme
     */
    public List<Word> findByThemeId(Long themeId) {
        String sql = "SELECT * FROM words WHERE theme_id = ?";
        return jdbcTemplate.query(sql, new WordRowMapper(), themeId);
    }

    /**
     * Finds a random word from a specific theme.
     *
     * @param themeId the ID of the theme
     * @return an Optional containing a random Word if found, empty otherwise
     */
    public Optional<Word> findRandomWordByThemeId(Long themeId) {
        String sql = "SELECT * FROM words WHERE theme_id = ? ORDER BY RAND() LIMIT 1";
        return jdbcTemplate.query(sql, new WordRowMapper(), themeId)
                .stream()
                .findFirst();
    }

    /**
     * Checks if a word exists for a specific theme.
     *
     * @param word the word to check
     * @param themeId the ID of the theme
     * @return true if the word exists for the theme, false otherwise
     */
    public boolean existsByWordAndThemeId(String word, Long themeId) {
        String sql = "SELECT COUNT(*) FROM words WHERE word = ? AND theme_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, word, themeId);
        return count != null && count > 0;
    }

    /**
     * Retrieves all words from the database.
     *
     * @return list of all Word objects
     */
    public List<Word> findAll() {
        String sql = "SELECT * FROM words";
        return jdbcTemplate.query(sql, new WordRowMapper());
    }

    /**
     * RowMapper implementation for mapping ResultSet rows to Word objects.
     */
    private static class WordRowMapper implements RowMapper<Word> {
        @Override
        public Word mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Word.builder()
                    .id(rs.getLong("id"))
                    .word(rs.getString("word"))
                    .themeId(rs.getLong("theme_id"))
                    .build();
        }
    }
}