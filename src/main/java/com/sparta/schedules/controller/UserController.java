package com.sparta.schedules.controller;

import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class UserController {

    private final UserService authorService;

    @GetMapping("/{userId}")
    public ResponseEntity<Schedule> findByAuthorId(@PathVariable Long userId) {
        Schedule schedule = authorService.findById(userId);
        return ResponseEntity.ok(schedule);
    }
}
