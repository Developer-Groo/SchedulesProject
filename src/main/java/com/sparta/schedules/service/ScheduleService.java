package com.sparta.schedules.service;

import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.repository.Repository;
import com.sparta.schedules.repository.dto.ScheduleSearchConditionDto;
import com.sparta.schedules.repository.dto.ScheduleUpdateDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final Repository repository;

    public Schedule save(Schedule schedule) {
        return repository.save(schedule);
    }

    public Schedule update(Long id, ScheduleUpdateDto updateDto) {
        return repository.update(id, updateDto);
    }

    public Optional<Schedule> findById(Long id) {
        return repository.findById(id);
    }

    public List<Schedule> findAll(ScheduleSearchConditionDto conditionDto) {
        return repository.findAll(conditionDto);
    }

    public Schedule delete(Long id, String password) {
        return repository.delete(id, password);
    }
}