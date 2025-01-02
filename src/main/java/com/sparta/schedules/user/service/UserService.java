package com.sparta.schedules.user.service;

import com.sparta.schedules.user.entity.User;
import com.sparta.schedules.user.dto.request.UserSearchConditionDto;
import com.sparta.schedules.user.dto.request.UserUpdateDto;
import com.sparta.schedules.user.dto.response.UserResponseDto;
import com.sparta.schedules.exception.NotFoundException;
import com.sparta.schedules.user.repository.UserDynamicQueryRepository;
import com.sparta.schedules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository repository;
    private final UserDynamicQueryRepository queryRepository;

    // 유저 생성
    public UserResponseDto save(User user) {
        return new UserResponseDto(repository.save(user));
    }

    // 유저 조회 (ID)
    public User findById(Long userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Requested ID not found"));
    }

    // 유저 조회 (Name)
    public List<UserResponseDto> findByName(UserSearchConditionDto conditionDto) {
        Pageable pageRequest = PageRequest.of(conditionDto.getPage() - 1, conditionDto.getSize());
        return queryRepository.findByName(conditionDto, pageRequest);
    }

    // 유저 수정
    public UserResponseDto update(Long userId, UserUpdateDto updateDto) {
        User findUser = findById(userId);

        findUser.setName(updateDto.getName());
        findUser.setEmail(updateDto.getEmail());
        findUser.setPassword(updateDto.getPassword());

        return new UserResponseDto(findUser);
    }

    // 유저 삭제
    public UserResponseDto delete(Long user) {
        User findUser = findById(user);
        repository.delete(findUser);

        return new UserResponseDto(findUser);
    }
}
