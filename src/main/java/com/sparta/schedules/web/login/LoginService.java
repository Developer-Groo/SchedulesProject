package com.sparta.schedules.web.login;

import com.sparta.schedules.domain.User;
import com.sparta.schedules.dto.user.response.UserResponseDto;
import com.sparta.schedules.exception.IncorrectPasswordException;
import com.sparta.schedules.repository.user.UserRepository;
import com.sparta.schedules.web.config.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto login(String email, String password) {
        User user = userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if (user == null) {
            return null;
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IncorrectPasswordException("비밀번호가 맞지 않습니다.");
        }

        return new UserResponseDto(user);
    }
}
