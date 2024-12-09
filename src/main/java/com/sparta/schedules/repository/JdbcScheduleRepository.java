package com.sparta.schedules.repository;

import com.sparta.schedules.domain.Author;
import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.repository.dto.ScheduleRequestDto;
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
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class JdbcScheduleRepository implements ScheduleRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert scheduleInsert;
    private final SimpleJdbcInsert authorInsert;

    public JdbcScheduleRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.scheduleInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("schedule")
                .usingGeneratedKeyColumns("schedule_id");
        this.authorInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("author")
                .usingGeneratedKeyColumns("author_id");
    }

    @Override
    public Schedule save(ScheduleRequestDto requestDto) {
        Author author = requestDto.getAuthor();
        Schedule schedule = requestDto.getSchedule();

        SqlParameterSource authorParam = new BeanPropertySqlParameterSource(author);
        Number authorKey = authorInsert.executeAndReturnKey(authorParam);
        author.setAuthorId(authorKey.longValue());
        schedule.setAuthorId(authorKey.longValue());

        SqlParameterSource scheduleParam = new BeanPropertySqlParameterSource(schedule);
        Number scheduleKey = scheduleInsert.executeAndReturnKey(scheduleParam);
        schedule.setScheduleId(scheduleKey.longValue());

        return schedule;
    }

    @Override
    public void update(Long scheduleId, ScheduleUpdateDto updateDto) {
        String sql = "update author a " +
                "join schedule s on a.author_id = s.author_id " +
                "set s.todo = :todo, a.name = :name " +
                "where s.schedule_id = :scheduleId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("todo", updateDto.getTodo());
        params.addValue("name", updateDto.getAuthorName());
        params.addValue("scheduleId", scheduleId);

        template.update(sql, params);
    }

    @Override
    public Optional<Schedule> findById(Long scheduleId) {
        String sql = "select schedule_id, todo, password, created_at, updated_at, author_id " +
                "from schedule where schedule_id = :scheduleId";

        try {
            HashMap<String, Object> param = new HashMap<>();
            param.put("scheduleId", scheduleId);
            Schedule schedule = template.queryForObject(sql, param, rowMapper());
            return Optional.of(schedule);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Schedule> findAll(ScheduleSearchConditionDto conditionDto) {
        String name = conditionDto.getName();
        LocalDateTime updatedAt = conditionDto.getUpdatedAt();
        int size = conditionDto.getSize();
        int page = conditionDto.getPage();

        StringBuilder sql = new StringBuilder(
                "select s.schedule_id, s.todo, s.created_at, s.updated_at, s.author_id, a.name " +
                "from schedule s " +
                "join author a on s.author_id = a.author_id"
        );

        HashMap<String, Object> params = new HashMap<>();

        if ((name != null && !name.isBlank()) || updatedAt != null) {
            boolean flag = false;

            sql.append(" where");

            if (name != null && !name.isBlank()) {
                sql.append(" a.name = :name");
                params.put("name", name);
                flag = true;
            }

            if (updatedAt != null) {
                if (flag) {
                    sql.append(" and");
                }
                sql.append(" s.updated_at >= :updatedAt");
                params.put("updatedAt", updatedAt);
            }
        }

        sql.append(" order by s.updated_at desc");

        sql.append(" limit :size offset :offset");
        params.put("size", size);
        params.put("offset", (page - 1) * size);

        return template.query(sql.toString(), params, rowMapper());
    }

    @Override
    public Optional<Schedule> delete(Long scheduleId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("scheduleId", scheduleId);

        Optional<Schedule> byId = findById(scheduleId);

        String sql = "delete a, s " +
                "from author a " +
                "join schedule s on a.author_id = s.author_id " +
                "where s.schedule_id = :scheduleId";

        template.update(sql, params);
        return byId;
    }

    private RowMapper<Schedule> rowMapper() {
        return BeanPropertyRowMapper.newInstance(Schedule.class);
    }
}
