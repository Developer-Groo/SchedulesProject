package com.sparta.schedules.controller;

import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<Schedule>> getScheduleList(
            @RequestParam(required = false) String authorId,
            @RequestParam(required = false) LocalDateTime updatedAt,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
            ) {
        // 임시 return 값
        return ResponseEntity.ok(List.of());
    }

    @PostMapping
    public Schedule createSchedule(@RequestBody Schedule schedule) {
        return schedule;
    }

    @PutMapping("/{scheduleId}")
    public String updateSchedule(@PathVariable Long scheduleId, @RequestBody Schedule schedule) {
        // 임시 return 값
        return "OK";
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Schedule> deleteSchedule(@PathVariable Long scheduleId, @RequestBody Schedule schedule) {
        // 임시 return 값
        return ResponseEntity.ok(new Schedule());
    }
}
