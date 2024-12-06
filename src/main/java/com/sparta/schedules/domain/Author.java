package com.sparta.schedules.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Author {

    private final Long authorId;

    private String name;

    private String email;

    private String createdAt;

    private String updatedAt;
}
