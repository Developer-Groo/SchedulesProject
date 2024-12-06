package com.sparta.schedules.repository;

import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.repository.dto.ScheduleSearchConditionDto;
import com.sparta.schedules.repository.dto.ScheduleUpdateDto;

import java.util.List;
import java.util.Optional;

/**
 * 추후 JPA 적용을 위한 인터페이스 적용
 */
public interface Repository {

    Schedule save(Schedule schedule);

    void update(Long id, ScheduleUpdateDto updateDto);

    Optional<Schedule> findById(Long id);

    List<Schedule> findAll(ScheduleSearchConditionDto conditionDto);

    Schedule delete(Long id, String password);
}
