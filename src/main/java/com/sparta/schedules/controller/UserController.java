package com.sparta.schedules.controller;

import com.sparta.schedules.domain.User;
import com.sparta.schedules.dto.user.request.UserForm;
import com.sparta.schedules.dto.user.request.UserSearchConditionDto;
import com.sparta.schedules.dto.user.request.UserUpdateDto;
import com.sparta.schedules.dto.user.response.UserResponseDto;
import com.sparta.schedules.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home/user")
public class UserController {

    private final UserService userService;

    // 유저 생성
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserForm form) {
        User user = new User();
        user.setName(form.getName());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());

        UserResponseDto savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }

    // 유저 조회 (ID)
    @GetMapping("/id/{userId}")
    public ResponseEntity<UserResponseDto> findByUserId(@PathVariable Long userId) {
        User user = userService.findById(userId);
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
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId, @RequestBody @Valid UserUpdateDto updateDto) {
        UserResponseDto updateUser = userService.update(userId, updateDto);
        return ResponseEntity.ok(updateUser);
    }

    // 유저 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable Long userId) {
        UserResponseDto deleteUser = userService.delete(userId);
        return ResponseEntity.ok(deleteUser);
    }
}
