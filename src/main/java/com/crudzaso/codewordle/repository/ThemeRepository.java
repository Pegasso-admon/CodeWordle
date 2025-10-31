package com.crudzaso.codewordle.repository;

import com.crudzaso.codewordle.model.Theme;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Repository for managing Theme entities in the database.
 * Provides operations for retrieving theme information.
 */
@Repository
@RequiredArgsConstructor
public class ThemeRepository {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Retrieves all themes from the database.
     *
     * @return list of all Theme objects
     */
    public List<Theme> findAll() {
        String sql = "SELECT * FROM themes ORDER BY name";
        return jdbcTemplate.query(sql, new ThemeRowMapper());
    }

    /**
     * Finds a theme by its ID.
     *
     * @param id the ID of the theme to find
     * @return an Optional containing the Theme if found, empty otherwise
     */
    public Optional<Theme> findById(Long id) {
        String sql = "SELECT * FROM themes WHERE id = ?";
        return jdbcTemplate.query(sql, new ThemeRowMapper(), id)
                .stream()
                .findFirst();
    }

    /**
     * Finds a theme by its name.
     *
     * @param name the name of the theme to find
     * @return an Optional containing the Theme if found, empty otherwise
     */
    public Optional<Theme> findByName(String name) {
        String sql = "SELECT * FROM themes WHERE name = ?";
        return jdbcTemplate.query(sql, new ThemeRowMapper(), name)
                .stream()
                .findFirst();
    }

    /**
     * RowMapper implementation for mapping ResultSet rows to Theme objects.
     */
    private static class ThemeRowMapper implements RowMapper<Theme> {
        @Override
        public Theme mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Theme.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .build();
        }
    }
}