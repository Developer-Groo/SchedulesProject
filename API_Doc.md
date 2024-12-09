# API Document

### ☑️ 일정 조회

**일정 목록을 조회합니다.**

~~~ http
GET /schedules
~~~

<br>

### Request

~~~ json
// 빈 json 요청 - 전체조회
{ }
~~~

| Property | Type | Description | Required |
|--------|--------|--------|-------------|
| `name` | String | 특정 작성자의 이름 | Optional |
| `updated_at` | LocalDate | 특정 날짜 (YYYY-MM-DD 형식) | Optional |
| `page` | int | 검색할 페이지 번호 | Optional, default: 1 |
| `size` | int | 페이지당 항목 수 | Optional, default: 10 |

<br>

### Response
~~~ json
[
    {
        "scheduleId": 17,
        "todo": "today project",
        "password": null,
        "createdAt": "2024-12-09T10:00:00",
        "updatedAt": "2024-12-09T23:44:06",
        "authorId": 18
    },
   ...
]
~~~

<br>

### Error

~~~ json
검색 조건의 데이터가 없는 경우
[]
~~~

<br>

### - 일정 생성

**새로운 일정을 생성합니다.**

~~~ http
POST /schedules
~~~

<br>

### Request

**json**
| Body | Type | Description | Required |
|------|------|-------------|----------|
| `todo` | String | 일정 내용 | Required |
| `author_name` | String | 작성자의 이름 | Required |
| `email` | String | 이메일 주소 | Required |
| `password` | String | 비밀번호 | Required |

<br>

### Response
~~~ json
{
    "scheduleId": 10,
    "message": "Schedule created successfully."
}
~~~

<br>

### Error

~~~ json
400 Bad Request - 필수 필드 누락
{
    "error": "Missing required fields.",
    "details": "'todo' and 'password' are required."
}
~~~

<br>

### - 일정 수정

**일정을 수정 합니다.**

~~~ http
PUT /schedules/{id}
~~~

<br>

### Request

| Parameter | Type | Description | Required |
|-----------|------|-------------|----------|
| `schedule_id` | Query | 일정의 id 값 (Required) | Required |

**json**
| Body | Type | Description | Required |
|------|------|-------------|----------|
| `todo` | String | 수정할 일정 내용 | Optional |
| `author_name` | String | 수정할 작성자의 이름 | Optional |
| `password` | String | 비밀번호 | Required |

<br>

### Response
~~~ json
{
    "scheduleId": 10,
    "message": "Schedule updated successfully."
}
~~~

<br>

### Error

~~~ json
403 Forbidden - 비밀번호가 일치하지 않을 경우
{
    "error": "Invalid password.",
    "details": "Password does not match for schedule ID 10."
}
~~~

<br>

### - 일정 삭제

**일정을 삭제 합니다.**

~~~ http
DELETE /schedules/{id}
~~~

<br>

### Request

| Parameter | Type | Description | Required |
|-----------|------|-------------|----------|
| `schedule_id` | Query | 일정의 id 값 | Required |

**json**
| Body | Stirng | Description | Required |
|------|--------|-------------|----------|
| `password` | String |비밀번호 | Required |

<br>

### Response
~~~ json
{
    "scheduleId": 10,
    "message": "Schedule deleted successfully."
}
~~~

<br>

### Error

~~~ json
403 Forbidden - 비밀번호가 일치하지 않을 경우
{
    "error": "Invalid password.",
    "details": "Password does not match for schedule ID 10."
}

404 Not Found - 해당 일정이 없는 경우
{
    "error": "Schedule not found.",
    "details": "No schedule found with ID 10."
}
~~~

<br>

