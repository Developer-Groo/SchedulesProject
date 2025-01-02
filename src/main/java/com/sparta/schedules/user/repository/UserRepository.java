package com.sparta.schedules.repository.user;

import com.sparta.schedules.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
