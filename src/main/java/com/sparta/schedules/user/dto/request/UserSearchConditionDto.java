package com.sparta.schedules.user.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSearchConditionDto {

    private String name;

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;
}
