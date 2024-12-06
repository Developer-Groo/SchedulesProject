package com.sparta.schedules.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Schedule {

    private final Long scheduleId;

    private String todo;

    private String password;

    private String createdAt;

    private String updatedAt;
}
