package com.sparta.schedules.controller;

import com.sparta.schedules.domain.Author;
import com.sparta.schedules.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/author_id={authorId}")
    public ResponseEntity<Author> findByAuthorId(@PathVariable Long authorId) {
        Optional<Author> byId = authorService.findById(authorId);
        return ResponseEntity.ok(byId.get());
    }
}
