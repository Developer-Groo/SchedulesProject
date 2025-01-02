package com.sparta.schedules.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleForm {

    @NotBlank(message = "제목(title)은 필수입니다.")
    @Size(max = 100, message = "제목(title)은 최대 100자까지 입력 가능합니다.")
    private String todoTitle;

    @NotBlank(message = "할일(todo)은 필수입니다.")
    @Size(max = 200, message = "할일(todo)은 최대 200자까지 입력 가능합니다.")
    private String todoContent;

    private Long userId;
}
