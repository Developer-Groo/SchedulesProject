package com.sparta.schedules.service;

import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.exception.IncorrectPasswordException;
import com.sparta.schedules.exception.NotFoundException;
import com.sparta.schedules.repository.ScheduleRepository;
import com.sparta.schedules.dto.ScheduleRequestDto;
import com.sparta.schedules.dto.ScheduleSearchConditionDto;
import com.sparta.schedules.dto.ScheduleUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository;

    public Schedule save(ScheduleRequestDto requestDto) {
        return repository.save(requestDto);
    }

    public void update(Long scheduleId, ScheduleUpdateDto updateDto) {
        Schedule schedule = findById(scheduleId);
        verifyPassword(schedule, updateDto.getPassword());

        repository.update(scheduleId, updateDto);
    }

    public Schedule findById(Long scheduleId) {
        return repository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundException("Requested ID not found"));
    }

    public List<Schedule> findAll(ScheduleSearchConditionDto conditionDto) {
        return repository.findAll(conditionDto);
    }

    public Schedule delete(Long scheduleId, String password) {
        Schedule schedule= findById(scheduleId);
        verifyPassword(schedule, password);

        return repository.delete(scheduleId)
                .orElseThrow(() -> new NotFoundException("Requested ID not found"));
    }

    private void verifyPassword(Schedule schedule, String password) {
        if (!schedule.getPassword().equals(password)) {
            throw new IncorrectPasswordException("Password does not match");
        }
    }
}
