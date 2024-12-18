package com.sparta.schedules.service;

import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.exception.NotFoundException;
import com.sparta.schedules.repository.jdbc.JdbcUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JdbcUserRepository repository;

    public Schedule findById(Long userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Requested ID not found"));
    }
}
