package com.sparta.schedules.repository;

import com.sparta.schedules.domain.Author;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Optional;

@Repository
public class JdbcAuthorRepository {

    private final NamedParameterJdbcTemplate template;

    public JdbcAuthorRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    public Optional<Author> findById(Long authorId) {
        String sql = "select author_id, name, email, created_at, updated_at " +
                "from author where author_id = :authorId";

        try {
            HashMap<String, Object> param = new HashMap<>();
            param.put("authorId", authorId);
            Author author = template.queryForObject(sql, param, rowMapper());
            return Optional.of(author);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<Author> rowMapper() {
        return BeanPropertyRowMapper.newInstance(Author.class);
    }
}
