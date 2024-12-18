package com.sparta.schedules.service;

import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.dto.schedule.response.ScheduleResponseDto;
import com.sparta.schedules.exception.NotFoundException;
import com.sparta.schedules.dto.schedule.request.ScheduleSearchConditionDto;
import com.sparta.schedules.dto.schedule.request.ScheduleUpdateDto;
import com.sparta.schedules.repository.schedule.ScheduleDynamicQueryRepository;
import com.sparta.schedules.repository.schedule.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository repository;
    private final ScheduleDynamicQueryRepository queryRepository;

    // 일정 생성
    public ScheduleResponseDto save(Schedule schedule) {
        return new ScheduleResponseDto(repository.save(schedule));
    }

    // 일정 조회 (ID)
    public Schedule findById(Long scheduleId) {
        return repository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundException("Requested ID not found"));
    }

    // 전체 일정 조회
    public List<ScheduleResponseDto> findAll(ScheduleSearchConditionDto conditionDto) {
        Pageable pageRequest = PageRequest.of(conditionDto.getPage() - 1, conditionDto.getSize());
        return queryRepository.findAll(conditionDto, pageRequest);
    }

    // 일정 수정
    public ScheduleResponseDto update(Long scheduleId, ScheduleUpdateDto updateDto) {
        Schedule findSchedule = findById(scheduleId);
        String todoTitle = updateDto.getTodoTitle();
        String todoContent = updateDto.getTodoContent();

        findSchedule.setTodoTitle(todoTitle);
        findSchedule.setTodoContent(todoContent);

        return new ScheduleResponseDto(findSchedule);
    }

    // 일정 삭제
    public ScheduleResponseDto delete(Long scheduleId) {
        Schedule schedule = findById(scheduleId);
        repository.delete(schedule);
        return new ScheduleResponseDto(schedule);
    }
}
