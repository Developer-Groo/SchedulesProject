package com.sparta.schedules.repository.schedule;

import com.sparta.schedules.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
