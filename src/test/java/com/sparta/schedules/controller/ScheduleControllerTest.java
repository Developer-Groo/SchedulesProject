package com.sparta.schedules.controller;

import com.sparta.schedules.domain.User;
import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.dto.ScheduleRequestDto;
import com.sparta.schedules.dto.ScheduleSearchConditionDto;
import com.sparta.schedules.dto.ScheduleUpdateDto;
import com.sparta.schedules.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleControllerTest {

    @InjectMocks
    private ScheduleController scheduleController;

    @Mock
    private ScheduleService scheduleService;

    @Test
    void getScheduleList() {
        // given
        ScheduleSearchConditionDto conditionDto = new ScheduleSearchConditionDto();
        List<Schedule> mockSchedules = List.of(new Schedule(), new Schedule());

        // when
        when(scheduleService.findAll(conditionDto)).thenReturn(mockSchedules);
        ResponseEntity<List<Schedule>> response = scheduleController.getScheduleList(conditionDto);

        // then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSchedules, response.getBody());
    }

    @Test
    void findByScheduleId() {
        // given
        Long scheduleId = 1L;
        Schedule mockSchedule = new Schedule();

        // when
        when(scheduleService.findById(scheduleId)).thenReturn(mockSchedule);
        ResponseEntity<Schedule> response = scheduleController.findByScheduleId(scheduleId);

        // then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSchedule, response.getBody());
    }

    @Test
    void createSchedule() {
        // given
        ScheduleRequestDto requestDto = new ScheduleRequestDto(new User(), new Schedule());
        Schedule mockSchedule = new Schedule();

        // when
        when(scheduleService.save(requestDto)).thenReturn(mockSchedule);
        ResponseEntity<Schedule> response = scheduleController.createSchedule(requestDto);

        // then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSchedule, response.getBody());
    }

    @Test
    void updateSchedule() {
        // given
        Long scheduleId = 1L;
        ScheduleUpdateDto updateDto = new ScheduleUpdateDto();

        // when
        doNothing().when(scheduleService).update(scheduleId, updateDto);
        ResponseEntity<String> response = scheduleController.updateSchedule(scheduleId, updateDto);

        // then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteSchedule() {
        // given
        Long scheduleId = 1L;
        String password = "password";
        Schedule mockSchedule = new Schedule();

        // when
        when(scheduleService.delete(scheduleId, password)).thenReturn(mockSchedule);
        ResponseEntity<Schedule> response = scheduleController.deleteSchedule(scheduleId, password);

        // then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSchedule, response.getBody());
    }
}