package com.sparta.schedules.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Schedule {

    private Long scheduleId;

    private String todo;

    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long authorId;

    public Schedule() {
    }

    public Schedule(String todo, String password, Long authorId) {
        this.todo = todo;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.authorId = authorId;
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
