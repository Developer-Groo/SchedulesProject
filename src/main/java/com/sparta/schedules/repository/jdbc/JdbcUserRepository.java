package com.sparta.schedules.repository.jdbc;

import com.sparta.schedules.domain.Schedule;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Optional;

@Repository
public class JdbcUserRepository {

    private final NamedParameterJdbcTemplate template;

    public JdbcUserRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    public Optional<Schedule> findById(Long userId) {
        String sql = "select s.schedule_id, s.todo, s.created_at, s.updated_at, a.author_id " +
                "from schedule s " +
                "join author a on s.author_id = a.author_id " +
                "where a.author_id = :authorId";

        try {
            HashMap<String, Object> param = new HashMap<>();
            param.put("authorId", userId);
            Schedule schedule = template.queryForObject(sql, param, rowMapper());
            return Optional.of(schedule);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<Schedule> rowMapper() {
        return BeanPropertyRowMapper.newInstance(Schedule.class);
    }
}
