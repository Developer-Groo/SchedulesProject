package com.sparta.schedules.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SuccessResponseDto {

    private String message;
    private String redirectUrl;
}
