package com.sparta.schedules.web.login;

import com.sparta.schedules.auth.dto.SuccessResponseDto;
import com.sparta.schedules.user.dto.response.UserResponseDto;
import com.sparta.schedules.web.config.SessionAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginForm form, HttpServletRequest request) {
        UserResponseDto user = loginService.login(form.getEmail(), form.getPassword());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.LOGIN_MEMBER.getKey(), user);

        return ResponseEntity.ok(new SuccessResponseDto("로그인 성공", "/home/schedules"));
    }

    @PostMapping("/logout")
    public ResponseEntity<SuccessResponseDto> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok(new SuccessResponseDto("로그아웃 성공", null));
    }
}
