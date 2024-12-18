package com.sparta.schedules.dto.user.response;

import com.sparta.schedules.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDto {

    private Long userId;
    private String name;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
    }
}
