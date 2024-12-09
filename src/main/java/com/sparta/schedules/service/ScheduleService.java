package com.sparta.schedules.service;

import com.sparta.schedules.domain.Author;
import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.repository.ScheduleRepository;
import com.sparta.schedules.repository.dto.ScheduleRequestDto;
import com.sparta.schedules.repository.dto.ScheduleSearchConditionDto;
import com.sparta.schedules.repository.dto.ScheduleUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository;

    public Schedule save(ScheduleRequestDto requestDto) {
        return repository.save(requestDto);
    }

    public void update(Long scheduleId, ScheduleUpdateDto updateDto) {
        repository.update(scheduleId, updateDto);
    }

    public Optional<Schedule> findById(Long scheduleId) {
        return repository.findById(scheduleId);
    }

    public List<Schedule> findAll(ScheduleSearchConditionDto conditionDto) {
        return repository.findAll(conditionDto);
    }

    public boolean delete(Long id, String password) {
        return repository.delete(id, password);
    }
}
