package com.sparta.schedules.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
public class AuthorController {

    @GetMapping("/author_id={authorId}")
    public ResponseEntity<String> findByAuthorId(@PathVariable Long authorId) {
        // authorservice 에서 findbyid 메서드 호출
        // 반환된 author 객체 return
        return ResponseEntity.ok("");
    }
}
