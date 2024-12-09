package com.sparta.schedules.repository.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleSearchConditionDto {

    private String name;
    private LocalDateTime updatedAt;

    public ScheduleSearchConditionDto(String name, LocalDateTime updatedAt) {
        this.name = name;
        this.updatedAt = updatedAt;
    }
}
