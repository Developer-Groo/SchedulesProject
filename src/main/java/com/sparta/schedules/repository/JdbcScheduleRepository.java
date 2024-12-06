package com.sparta.schedules.repository;

import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.repository.dto.ScheduleSearchConditionDto;
import com.sparta.schedules.repository.dto.ScheduleUpdateDto;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JdbcScheduleRepository implements Repository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcScheduleRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource);
    }

    @Override
    public Schedule save(Schedule schedule) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(schedule);
        Number key = jdbcInsert.executeAndReturnKey(param);
        schedule.setScheduleId(key.longValue());
        return schedule;
    }

    @Override
    public void update(Long id, ScheduleUpdateDto updateDto) {

    }

    @Override
    public Optional<Schedule> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Schedule> findAll(ScheduleSearchConditionDto conditionDto) {
        return List.of();
    }

    @Override
    public Schedule delete(Long id, String password) {
        return null;
    }
}
