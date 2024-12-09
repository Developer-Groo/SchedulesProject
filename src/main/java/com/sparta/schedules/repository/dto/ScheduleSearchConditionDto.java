package com.sparta.schedules.repository.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleSearchConditionDto {

    private String name;
    private LocalDateTime updatedAt;

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;
}
