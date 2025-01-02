package com.sparta.schedules.auth.web.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SessionAttribute {
    LOGIN_MEMBER("login-member");

    private final String key;
}
