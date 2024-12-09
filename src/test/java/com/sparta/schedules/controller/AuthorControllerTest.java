package com.sparta.schedules.controller;

import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

    @InjectMocks
    private AuthorController authorController;

    @Mock
    private AuthorService authorService;

    @Test
    void findByAuthorId() {
        // given
        Long authorId = 1L;
        Schedule mockSchedule = new Schedule();

        // when
        when(authorService.findById(authorId)).thenReturn(mockSchedule);
        ResponseEntity<Schedule> response = authorController.findByAuthorId(authorId);

        // then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSchedule, response.getBody());
    }

}