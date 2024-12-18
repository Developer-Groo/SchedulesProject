# 유저 생성
CREATE TABLE user
(
    user_id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(50)  NOT NULL,
    email      VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NULL
);

# 일정 생성
CREATE TABLE schedule
(
    schedule_id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    todo_title   VARCHAR(100) NOT NULL,
    todo_content TEXT         NOT NULL,
    created_at   TIMESTAMP NULL,
    updated_at   TIMESTAMP NULL,
    user_id      BIGINT       NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
);

# 댓글 생성
CREATE TABLE comment
(
    comment_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    comment_content TEXT      NOT NULL,
    created_at      TIMESTAMP NULL,
    updated_at      TIMESTAMP NULL,
    user_id         BIGINT    NOT NULL,
    schedule_id     BIGINT    NOT NULL,
    FOREIGN KEY (schedule_id) REFERENCES schedule (schedule_id) ON DELETE CASCADE
);