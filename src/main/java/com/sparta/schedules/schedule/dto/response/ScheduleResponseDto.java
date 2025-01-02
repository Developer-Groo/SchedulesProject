package com.sparta.schedules.schedule.dto.response;

import com.sparta.schedules.schedule.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
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
