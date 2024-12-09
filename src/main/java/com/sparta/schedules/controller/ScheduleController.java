package com.sparta.schedules.controller;

import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.repository.dto.ScheduleRequestDto;
import com.sparta.schedules.repository.dto.ScheduleSearchConditionDto;
import com.sparta.schedules.repository.dto.ScheduleUpdateDto;
import com.sparta.schedules.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<Schedule>> getScheduleList(ScheduleSearchConditionDto conditionDto) {
        List<Schedule> scheduleList = scheduleService.findAll(conditionDto);
        return ResponseEntity.ok(scheduleList);
    }

    @GetMapping("/schedule_id={scheduleId}")
    public ResponseEntity<Schedule> findByScheduleId(@PathVariable Long scheduleId) {
        Optional<Schedule> byId = scheduleService.findById(scheduleId);
        return ResponseEntity.ok(byId.get());
    }

    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        Schedule savedSchedule = scheduleService.save(requestDto);
        return ResponseEntity.ok(savedSchedule);
    }

    @PutMapping("/schedule_id={scheduleId}")
    public String updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleUpdateDto updateDto) {
        scheduleService.update(scheduleId, updateDto);
        return "OK";
    }

    @DeleteMapping("/schedule_id={scheduleId}")
    public ResponseEntity<Boolean> deleteSchedule(@PathVariable Long scheduleId, @RequestBody String password) {
        boolean delete = scheduleService.delete(scheduleId, password);
        return ResponseEntity.ok(delete);
    }
}
