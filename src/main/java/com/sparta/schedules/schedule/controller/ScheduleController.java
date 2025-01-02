package com.sparta.schedules.schedule.controller;

import com.sparta.schedules.schedule.entity.Schedule;
import com.sparta.schedules.user.entity.User;
import com.sparta.schedules.schedule.dto.request.ScheduleForm;
import com.sparta.schedules.schedule.dto.request.ScheduleSearchConditionDto;
import com.sparta.schedules.schedule.dto.request.ScheduleUpdateDto;
import com.sparta.schedules.schedule.dto.response.ScheduleResponseDto;
import com.sparta.schedules.schedule.service.ScheduleService;
import com.sparta.schedules.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedSchedule.getScheduleId())
                .toUri();

        return ResponseEntity.created(location).body(savedSchedule);
    }

    // 일정 조회 (ID)
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findByScheduleId(@PathVariable Long id) {
        Schedule schedule = scheduleService.findById(id);
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
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody @Valid ScheduleUpdateDto updateDto) {
        ScheduleResponseDto updateSchedule = scheduleService.update(id, updateDto);
        return ResponseEntity.ok(updateSchedule);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> deleteSchedule(@PathVariable Long id) {
        ScheduleResponseDto deleteSchedule = scheduleService.delete(id);
        return ResponseEntity.ok(deleteSchedule);
    }
}
