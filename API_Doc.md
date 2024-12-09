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

{
    "name": "Groo",
    "updatedAt": "2024-12-09T10:00:00",
    "page": 1,
    "size": 10
}
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

### ☑️ 일정 생성

**새로운 일정을 생성합니다.**

~~~ http
POST /schedules
~~~

<br>

### Request

~~~ json
{
  "author": {
    "name": "groo",
    "email": "groo123@gmail.com"
  },
  "schedule": {
    "todo": "Finish the project",
    "password": "12345"
  }
}
~~~

| Property | Type | Description | Required |
|------|------|-------------|----------|
| `name` | String | 작성자의 이름 | Required |
| `email` | String | 이메일 주소 | Required |
| `todo` | String | 일정 내용 | Required |
| `password` | String | 비밀번호 | Required |

<br>

### Response
~~~ json
{
    "scheduleId": 21,
    "todo": "Finish the project",
    "password": "12345",
    "createdAt": "2024-12-09T10:00:00",
    "updatedAt": "2024-12-09T10:00:00",
    "authorId": 22
}
~~~

<br>

### Error

~~~ json
400 Bad Request - 필수 필드 누락
{
    "author.name": "이름은 필수입니다."
    ...
}
~~~

<br>

### ☑️ 일정 수정

**일정을 수정 합니다.**

~~~ http
PUT /schedules/schedule_id={scheduleId}
~~~

<br>

### Request

| Parameter | Type | Description | Required |
|-----------|------|-------------|----------|
| `schedule_id` | Long | 일정의 id 값 | Required |


~~~ json
{
    "todo": "today I learn",
    "authorName": "Groo",
    "password": "12345"
}
~~~

| Property | Type | Description | Required |
|------|------|-------------|----------|
| `todo` | String | 수정할 일정 내용 | Required |
| `authorName` | String | 수정할 작성자의 이름 | Required |
| `password` | String | 비밀번호 | Required |

<br>

### Response
~~~ json
{
    OK
}
~~~

<br>

### Error

~~~ json
401 Unauthorized - 비밀번호가 일치하지 않을 경우
{
    Password does not match
}
~~~

<br>

### ☑️ 일정 삭제

**일정을 삭제 합니다.**

~~~ http
DELETE /schedules/schedule_id={scheduleId}
~~~

<br>

### Request

| Parameter | Type | Description | Required |
|-----------|------|-------------|----------|
| `scheduleId` | Long | 일정의 id 값 | Required |


~~~ json
12345
~~~

| Text | Stirng | Description | Required |
|------|--------|-------------|----------|
| `password` | String | 비밀번호 | Required |

<br>

### Response
~~~ json
{
    "scheduleId": 23,
    "todo": "today I learn",
    "password": "12345",
    "createdAt": "2024-12-09T10:00:00",
    "updatedAt": "2024-12-10T02:22:10",
    "authorId": 24
}
~~~

<br>

### Error

~~~ json
401 Unauthorized - 비밀번호가 일치하지 않을 경우
{
    Password does not match
}

404 Not Found - 해당 일정이 없는 경우
{
    Requested ID not found
}
~~~

<br>

