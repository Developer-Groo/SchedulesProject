package com.sparta.schedules.controller;

import com.sparta.schedules.user.service.UserService;
import com.sparta.schedules.user.controller.UserController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    void findByUserId() {
        // given
//        Long userId = 1L;
//        Schedule mockSchedule = new Schedule();
//
//        // when
//        when(userService.findById(userId)).thenReturn(mockSchedule);
//        ResponseEntity<Schedule> response = userController.findByAuthorId(userId);
//
//        // then
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(mockSchedule, response.getBody());
    }

}