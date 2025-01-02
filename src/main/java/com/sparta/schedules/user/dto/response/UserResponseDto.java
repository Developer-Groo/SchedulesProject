package com.sparta.schedules.user.dto.response;

import com.sparta.schedules.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private Long userId;
    private String name;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
    }
}
