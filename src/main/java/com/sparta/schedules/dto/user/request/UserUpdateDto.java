package com.sparta.schedules.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {

    @NotBlank(message = "이름(name)은 필수입니다.")
    @Size(max = 50, message = "이름(name)은 최대 50자까지 입력 가능합니다.")
    private String name;

    @NotBlank(message = "이메일(e-mail)은 필수입니다.")
    @Email(message = "유효하지 않은 이메일 형식입니다.")
    private String email;

    @NotBlank(message = "비밀번호(password)는 필수입니다.")
    @Size(min = 10, max = 15 ,message = "비밀번호(password)는 최소 10~15자리까지 입력 가능합니다.")
    private String password;
}
