package com.sparta.schedules.auth.controller;

import com.sparta.schedules.auth.dto.response.SuccessResponseDto;
import com.sparta.schedules.auth.dto.request.SignUpDto;
import com.sparta.schedules.user.dto.response.UserResponseDto;
import com.sparta.schedules.auth.web.config.SessionAttribute;
import com.sparta.schedules.auth.dto.request.LoginForm;
import com.sparta.schedules.auth.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/signup")
    public ResponseEntity<SuccessResponseDto> signUp(@RequestBody @Valid SignUpDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(loginService.signUp(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginForm form, HttpServletRequest request) {
        UserResponseDto user = loginService.login(form.getEmail(), form.getPassword());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.LOGIN_MEMBER.getKey(), user);

        return ResponseEntity
                .ok(new SuccessResponseDto(HttpServletResponse.SC_OK, "로그인 성공"));
    }

    @PostMapping("/logout")
    public ResponseEntity<SuccessResponseDto> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity
                .ok(new SuccessResponseDto(HttpServletResponse.SC_OK, "로그아웃 성공"));
    }
}
