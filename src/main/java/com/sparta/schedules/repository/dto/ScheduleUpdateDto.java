package com.sparta.schedules.repository.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ScheduleUpdateDto {

    private String todo;
    private String authorName;
    private String password;
}
