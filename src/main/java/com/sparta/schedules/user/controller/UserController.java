package com.sparta.schedules.user.controller;

import com.sparta.schedules.user.entity.User;
import com.sparta.schedules.user.dto.request.UserForm;
import com.sparta.schedules.user.dto.request.UserSearchConditionDto;
import com.sparta.schedules.user.dto.request.UserUpdateDto;
import com.sparta.schedules.user.dto.response.UserResponseDto;
import com.sparta.schedules.user.service.UserService;
import com.sparta.schedules.auth.util.PasswordEncoder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // 유저 생성
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserForm form) {
        String encodedPassword = passwordEncoder.encode(form.getPassword());

        User user = new User(
                form.getName(),
                form.getEmail(),
                encodedPassword
        );

        UserResponseDto savedUser = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id/{id}")
                .buildAndExpand(savedUser.getUserId())
                .toUri();

        return ResponseEntity.created(location).body(savedUser);
    }

    // 유저 조회 (ID)
    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDto> findByUserId(@PathVariable Long id) {
        User user = userService.findById(id);
        UserResponseDto findUser = new UserResponseDto(user);
        return ResponseEntity.ok(findUser);
    }

    // 유저 조회 (Name)
    @GetMapping("/name")
    public ResponseEntity<List<UserResponseDto>> findByUserName(@RequestBody UserSearchConditionDto conditionDto) {
        List<UserResponseDto> userList = userService.findByName(conditionDto);
        return ResponseEntity.ok(userList);
    }

    // 유저 수정
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDto updateDto) {
        UserResponseDto updateUser = userService.update(id, updateDto);
        return ResponseEntity.ok(updateUser);
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable Long id) {
        UserResponseDto deleteUser = userService.delete(id);
        return ResponseEntity.ok(deleteUser);
    }
}
