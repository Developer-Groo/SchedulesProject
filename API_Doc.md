# API Document

### - 일정 조회

**일정 목록을 조회합니다.**

~~~ http
GET /schedules
~~~

<br>

### Request

| Parameter | Description |
|--------|----------------|
| `author_id` | 특정 작성자의 id (optional) |
| `updated_at` | 특정 작성자의 id (optional) |
| `page` | 검색할 페이지 번호 (optional, default: 1) |
| `size` | 페이지당 항목 수 (optional, default: 10) |

<br>

### Response
~~~ json
{
    "totalCount": 25,
    "page": 1,
    "size": 10,
    "todos": [
        {
            "scheduleId": 1,
            "todo": "Complete project",
            "authorName": "user name",
            "createdAt": "2024-12-06T12:00:00",
            "updatedAt": "2024-12-06T13:00:00"
        },
        ...
    ]
}
~~~

<br>

### Error

~~~ json
404 Not Found - 해당 작성자가 없는 경우
{
    "error": "No schedules found.",
    "details": "No schedules for author with ID 1."
}

400 Bad Request - 잘못된 날짜 형식
{
    "error": "Invalid date format.",
    "details": "Expected format is 'YYYY-MM-DD'."
}
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
| Body | Description |
|--------|----------------|
| `todo` | 일정 내용 (Required) |
| `authorName` | 작성자의 이름 (Required) |
| `email` | 이메일 주소 (Required) |
| `password` | 비밀번호 (Required) |

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

| Parameter | Description |
|--------|----------------|
| `id` | 일정의 id 값 (Required) |

**json**
| Body | Description |
|--------|----------------|
| `todo` | 수정할 일정 내용 (Optional) |
| `authorName` | 수정할 작성자의 이름 (Optional) |
| `password` | 비밀번호 (Required) |

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

| Parameter | Description |
|--------|----------------|
| `id` | 일정의 id 값 (Required) |

**json**
| Parameter | Description |
|--------|----------------|
| `password` | 비밀번호 (Required) |

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

