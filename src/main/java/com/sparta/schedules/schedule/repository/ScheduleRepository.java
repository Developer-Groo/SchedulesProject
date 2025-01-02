package com.sparta.schedules.schedule.repository;

import com.sparta.schedules.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
