## 전체 일정 조회

**전체 일정 목록을 조회합니다.**

~~~ http
GET /schedules
~~~

<br>

| Parameter | Description |
|--------|----------------|
| `page` | 검색할 페이지 번호 (optional, default: 1) |
| `size` | 페이지당 항목 수 (optional, default: 10) |


### 응답 데이터
~~~ json
{
    "totalCount": 25,
    "page": 1,
    "size": 10,
    "data": [
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
