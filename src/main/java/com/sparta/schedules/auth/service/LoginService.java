package com.sparta.schedules.auth.service;

import com.sparta.schedules.auth.dto.request.SignUpDto;
import com.sparta.schedules.auth.dto.response.SuccessResponseDto;
import com.sparta.schedules.user.entity.User;
import com.sparta.schedules.user.dto.response.UserResponseDto;
import com.sparta.schedules.exception.IncorrectPasswordException;
import com.sparta.schedules.user.repository.UserRepository;
import com.sparta.schedules.auth.util.PasswordEncoder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SuccessResponseDto signUp(SignUpDto dto) {
        User user = new User(dto.name(), dto.email(), dto.password());

        User savedUser = userRepository.save(user);

        return new SuccessResponseDto(HttpServletResponse.SC_CREATED, "회원가입 성공");
    }

    @Transactional
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
