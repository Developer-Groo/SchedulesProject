# 작성자 생성
CREATE TABLE author (
                        author_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(50) NOT NULL,
                        email VARCHAR(50) NOT NULL UNIQUE,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

# 일정 생성
CREATE TABLE schedule (
                          schedule_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          todo VARCHAR(200) NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          author_id BIGINT NOT NULL,
                          FOREIGN KEY (author_id) REFERENCES author(author_id)
);