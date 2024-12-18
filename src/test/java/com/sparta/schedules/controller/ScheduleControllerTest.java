package com.sparta.schedules.controller;

import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.dto.schedule.request.ScheduleForm;
import com.sparta.schedules.dto.schedule.request.ScheduleSearchConditionDto;
import com.sparta.schedules.dto.schedule.request.ScheduleUpdateDto;
import com.sparta.schedules.dto.schedule.response.ScheduleResponseDto;
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
        List<ScheduleResponseDto> mockSchedules = List.of(new ScheduleResponseDto(), new ScheduleResponseDto());

        // when
        when(scheduleService.findAll(conditionDto)).thenReturn(mockSchedules);
        ResponseEntity<List<ScheduleResponseDto>> response = scheduleController.getScheduleList(conditionDto);

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
        ResponseEntity<ScheduleResponseDto> response = scheduleController.findByScheduleId(scheduleId);

        // then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createSchedule() {
        // given
        Schedule requestDto = new Schedule();
        ScheduleForm scheduleForm = new ScheduleForm();
        ScheduleResponseDto mockSchedule = new ScheduleResponseDto();

        // when
        when(scheduleService.save(requestDto)).thenReturn(mockSchedule);
        ResponseEntity<ScheduleResponseDto> response = scheduleController.createSchedule(scheduleForm);

        // then
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockSchedule, response.getBody());
    }

    @Test
    void updateSchedule() {
        // given
        Long scheduleId = 1L;
        ScheduleUpdateDto updateDto = new ScheduleUpdateDto();

        // when
        doNothing().when(scheduleService).update(scheduleId, updateDto);
        ResponseEntity<ScheduleResponseDto> response = scheduleController.updateSchedule(scheduleId, updateDto);

        // then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteSchedule() {
        // given
        Long scheduleId = 1L;
        String password = "password";
        ScheduleResponseDto mockSchedule = new ScheduleResponseDto();

        // when
        when(scheduleService.delete(scheduleId)).thenReturn(mockSchedule);
        ResponseEntity<ScheduleResponseDto> response = scheduleController.deleteSchedule(scheduleId);

        // then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSchedule, response.getBody());
    }
}