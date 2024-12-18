package com.sparta.schedules.dto;

import com.sparta.schedules.domain.User;
import com.sparta.schedules.domain.Schedule;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ScheduleRequestDto {

    @Valid
    private final User user;

    @Valid
    private final Schedule schedule;
}
