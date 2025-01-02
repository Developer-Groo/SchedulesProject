package com.sparta.schedules.user.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.schedules.user.dto.request.UserSearchConditionDto;
import com.sparta.schedules.user.dto.response.UserResponseDto;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.sparta.schedules.user.entity.QUser.user;

@Repository
public class UserDynamicQueryRepository {

    private final JPAQueryFactory queryFactory;

    public UserDynamicQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<UserResponseDto> findByName(UserSearchConditionDto conditionDto, Pageable pageable) {
        return queryFactory.select(Projections.constructor(UserResponseDto.class,
                        user.userId,
                        user.name))
                .from(user)
                .where(likeUserName(conditionDto.getName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    // 유저 이름 기준 검색 조건
    private BooleanExpression likeUserName(String userName) {
        return StringUtils.hasText(userName) ? user.name.containsIgnoreCase(userName) : null;
    }
}
