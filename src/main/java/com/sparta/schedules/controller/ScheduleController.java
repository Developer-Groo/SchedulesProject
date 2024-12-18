package com.sparta.schedules.controller;

import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.domain.User;
import com.sparta.schedules.dto.schedule.request.ScheduleForm;
import com.sparta.schedules.dto.schedule.request.ScheduleSearchConditionDto;
import com.sparta.schedules.dto.schedule.request.ScheduleUpdateDto;
import com.sparta.schedules.dto.schedule.response.ScheduleResponseDto;
import com.sparta.schedules.service.ScheduleService;
import com.sparta.schedules.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserService userService;

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody @Valid ScheduleForm form) {
        User user = userService.findById(form.getUserId());
        Schedule schedule = new Schedule();
        schedule.setTodoTitle(form.getTodoTitle());
        schedule.setTodoContent(form.getTodoContent());
        schedule.setUser(user);

        ScheduleResponseDto savedSchedule = scheduleService.save(schedule);
        return ResponseEntity.ok(savedSchedule);
    }

    // 일정 조회 (ID)
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findByScheduleId(@PathVariable Long scheduleId) {
        Schedule schedule = scheduleService.findById(scheduleId);
        ScheduleResponseDto findSchedule = new ScheduleResponseDto(schedule);
        return ResponseEntity.ok(findSchedule);
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getScheduleList(@RequestBody ScheduleSearchConditionDto conditionDto) {
        List<ScheduleResponseDto> scheduleList = scheduleService.findAll(conditionDto);
        return ResponseEntity.ok(scheduleList);
    }

    // 일정 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long scheduleId, @RequestBody @Valid ScheduleUpdateDto updateDto) {
        ScheduleResponseDto updateSchedule = scheduleService.update(scheduleId, updateDto);
        return ResponseEntity.ok(updateSchedule);
    }

    // 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> deleteSchedule(@PathVariable Long scheduleId) {
        ScheduleResponseDto deleteSchedule = scheduleService.delete(scheduleId);
        return ResponseEntity.ok(deleteSchedule);
    }
}
