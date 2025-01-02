package com.sparta.schedules.schedule.dto.request;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleSearchConditionDto {

    private String name;

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;
}
