package com.sparta.schedules.repository.dto;

import com.sparta.schedules.domain.Author;
import com.sparta.schedules.domain.Schedule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ScheduleRequestDto {

    private final Author author;

    private final Schedule schedule;
}
