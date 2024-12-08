package com.sparta.schedules.repository;

import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.repository.dto.ScheduleSearchConditionDto;
import com.sparta.schedules.repository.dto.ScheduleUpdateDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class JdbcScheduleRepository implements ScheduleRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcScheduleRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource);
        this.jdbcInsert.setTableName("schedule");
        this.jdbcInsert.usingGeneratedKeyColumns("schedule_id");
    }

    @Override
    public Schedule save(Schedule schedule) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(schedule);
        Number key = jdbcInsert.executeAndReturnKey(param);
        schedule.setScheduleId(key.longValue());
        return schedule;
    }

    @Override
    public boolean update(Long scheduleId, ScheduleUpdateDto updateDto) {
        String sql = "update author a " +
                "join schedule s on a.author_id = s.author_id " +
                "set s.todo = :todo, a.name = :name " +
                "where s.schedule_id = :scheduleId";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("todo", updateDto.getTodo());
        param.addValue("name", updateDto.getAuthorName());
        param.addValue("schedule_id", scheduleId);

        template.update(sql, param);
        return true;
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        String sql = "select schedule_id, todo, password, created_at, updated_at " +
                "from schedule where id = :id";

        try {
            Map<String, Long> param = Map.of("id", id);
            Schedule schedule = template.queryForObject(sql, param, rowMapper());
            return Optional.of(schedule);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Schedule> findAll(ScheduleSearchConditionDto conditionDto) {
        return List.of();
    }

    @Override
    public boolean delete(Long scheduleId, String password) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("scheduleId", scheduleId);
        params.put("password", password);

        String sql = "delete a, s " +
                "from author a " +
                "join schedule s on a.author_id = s.author_id " +
                "where s.schedule_id = :scheduleId";

        template.update(sql, params);

        return true;
    }

    private RowMapper<Schedule> rowMapper() {
        return BeanPropertyRowMapper.newInstance(Schedule.class);
    }
}
