package com.sparta.schedules.user.repository;

import com.sparta.schedules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
