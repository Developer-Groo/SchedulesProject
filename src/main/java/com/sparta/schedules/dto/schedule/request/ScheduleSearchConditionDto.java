package com.sparta.schedules.dto.schedule.request;

import lombok.*;

@Getter
@Setter
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
