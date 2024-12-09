package com.sparta.schedules.service;

import com.sparta.schedules.domain.Author;
import com.sparta.schedules.domain.Schedule;
import com.sparta.schedules.repository.JdbcAuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final JdbcAuthorRepository repository;

    public Schedule findById(Long authorId) {
        return repository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
