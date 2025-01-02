package com.sparta.schedules.dto.schedule.response;

import com.sparta.schedules.domain.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleResponseDto {

    private Long scheduleId;
    private String todoTitle;
    private String todoContent;

    private Long userId;
    private String name;

    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.todoTitle = schedule.getTodoTitle();
        this.todoContent = schedule.getTodoContent();
        this.userId = schedule.getUser().getUserId();
        this.name = schedule.getUser().getName();
    }
}
