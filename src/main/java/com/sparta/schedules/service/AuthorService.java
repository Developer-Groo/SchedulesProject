package com.sparta.schedules.service;

import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.exception.NotFoundException;
import com.sparta.schedules.repository.JdbcAuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final JdbcAuthorRepository repository;

    public Schedule findById(Long authorId) {
        return repository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Requested ID not found"));
    }
}
