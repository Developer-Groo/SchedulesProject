package com.sparta.schedules.repository.dto;

import com.sparta.schedules.domain.Author;
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
    private final Author author;

    @Valid
    private final Schedule schedule;
}
