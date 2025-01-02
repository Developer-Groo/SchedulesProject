package com.sparta.schedules.schedule.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.schedules.schedule.dto.request.ScheduleSearchConditionDto;
import com.sparta.schedules.schedule.dto.response.ScheduleResponseDto;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.sparta.schedules.schedule.entity.QSchedule.schedule;
import static com.sparta.schedules.user.entity.QUser.user;

@Repository
public class ScheduleDynamicQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ScheduleDynamicQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @EntityGraph(attributePaths = {"user"})
    public List<ScheduleResponseDto> findAll(ScheduleSearchConditionDto condition, Pageable pageable) {
        return queryFactory.select(Projections.constructor(ScheduleResponseDto.class,
                        schedule.scheduleId,
                        schedule.todoTitle,
                        schedule.todoContent,
                        user.userId,
                        user.name))
                .from(schedule)
                .join(schedule.user, user)
                .where(likeUserName(condition.getName()))
                .orderBy(schedule.updatedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    // 작성자 기준 검색 조건
    private BooleanExpression likeUserName(String userName) {
        return StringUtils.hasText(userName) ? schedule.user.name.containsIgnoreCase(userName) : null;
    }
}
