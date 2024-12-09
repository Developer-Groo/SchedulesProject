package com.sparta.schedules.repository.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleUpdateDto {

    @NotBlank(message = "할일(todo)은 필수입니다.")
    @Size(max = 200, message = "할일(todo)은 최대 200자까지 입력 가능합니다.")
    private String todo;

    @NotBlank(message = "이름은 필수입니다.")
    private String authorName;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
