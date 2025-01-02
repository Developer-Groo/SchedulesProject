package com.sparta.schedules.schedule.entity;

import com.sparta.schedules.common.BaseEntity;
import com.sparta.schedules.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column(
            nullable = false
    )
    private String todoTitle;

    @Column(
            nullable = false
    )
    private String todoContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule(
            String todoTitle,
            String todoContent,
            User user
    ) {
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.user = user;
    }

    public void updateSchedule(String todoTitle, String todoContent) {
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
    }
}
