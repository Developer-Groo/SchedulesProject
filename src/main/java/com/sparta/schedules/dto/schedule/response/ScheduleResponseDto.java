package com.sparta.schedules.dto.schedule.response;

import com.sparta.schedules.domain.Schedule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ScheduleResponseDto {

    private final Long scheduleId;
    private final String todoTitle;
    private final String todoContent;

    private final Long userId;
    private final String name;

    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.todoTitle = schedule.getTodoTitle();
        this.todoContent = schedule.getTodoContent();
        this.userId = schedule.getUser().getUserId();
        this.name = schedule.getUser().getName();
    }
}
