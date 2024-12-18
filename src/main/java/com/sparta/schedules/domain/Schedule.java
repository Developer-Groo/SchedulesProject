package com.sparta.schedules.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Schedule {

    private Long scheduleId;

    @NotBlank(message = "할일(todo)은 필수입니다.")
    @Size(max = 200, message = "할일(todo)은 최대 200자까지 입력 가능합니다.")
    private String todo;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long userId;

    public Schedule() {
    }

    public Schedule(String todo, String password, Long authorId) {
        this.todo = todo;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.userId = authorId;
    }
}
