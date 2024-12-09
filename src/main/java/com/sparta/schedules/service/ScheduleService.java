package com.sparta.schedules.service;

import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.exception.IncorrectPasswordException;
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
        Optional<Schedule> byId = findById(scheduleId);
        verifyPassword(byId.get(), updateDto.getPassword());

        repository.update(scheduleId, updateDto);
    }

    public Optional<Schedule> findById(Long scheduleId) {
        return repository.findById(scheduleId);
    }

    public List<Schedule> findAll(ScheduleSearchConditionDto conditionDto) {
        return repository.findAll(conditionDto);
    }

    public Schedule delete(Long scheduleId, String password) {
        Optional<Schedule> byId = findById(scheduleId);
        verifyPassword(byId.get(), password);

        Optional<Schedule> delete = repository.delete(scheduleId);
        return delete.get();
    }

    private void verifyPassword(Schedule schedule, String password) {
        if (!schedule.getPassword().equals(password)) {
            throw new IncorrectPasswordException("Password does not match");
        }
    }
}
