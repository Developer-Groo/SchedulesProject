# 📆 SchedulesProject

**RestAPI 로 일정관리 웹애플리케이션을 구현하였습니다.**

## ☑️ Index
- [🏁 Team](#-Team)
- [📂 Library](#-Library)
- [📑 Commit Convention](#-Commit-Convention)   
- [🛜 API Document](#-API-Document)
- [🔗 ERD](#-ERD)
- [🔥 Trouble Shouting](#-Trouble-Shouting)

<br>

## 🏁 Team
|**우현**|
|--------|
|<img src="https://github.com/Developer-Nova/Sec19-Local-Data-Persistance_ByAngela/assets/123448121/17a2ba3b-a618-4ac8-93b9-0d0e02c19c78" width="110" height="110">|
|[Groo's GitHub](https://github.com/Developer-Groo)|

<br>

## 📂 Library

- Spring Web

- Lombok

- JDBC API

- H2 Database

- MySQL Driver

- Validation

<br>

## 📑 Commit Convention

**`feat`** : 새로운 기능 추가

**`fix`** : bug fix

**`docs`**  : 문서 수정

**`style`** : 세미콜론 같은 코드의 사소한 스타일 변화.

**`refactor`** : 변수명 수정같은 리팩터링

**`test`** : 테스트 코드 추가 & 수정

**`chore`** : 중요하지 않은 일

<br>

## 🛜 API Document

- [Document](https://github.com/Developer-Groo/SchedulesProject/blob/main/API_Doc.md)

<br>
<br>

## 🔗 ERD

~~~ mermaid

erDiagram
    USER {
        BIGINT user_id PK
        VARCHAR name
        VARCHAR email
        VARCHAR password
        TIMESTAMP created_at
        TIMESTAMP updated_at
    }
    SCHEDULE {
        BIGINT schedule_id PK
        VARCHAR todo_title
        TEXT todo_content
        TIMESTAMP created_at
        TIMESTAMP updated_at
        BIGINT user_id FK
    }

    USER ||--o| SCHEDULE : has

~~~

<br>
<br>

## 🔥 Trouble Shouting

### 1. Optionl 처리

아래 코드는 DB 에 접근하여 id 값을 통해 해당 데이터를 찾아 `Schedule` 객체에 매핑하는 코드 입니다.

만약 데이터가 없거나 매핑이 되지 않는 경우 `template.queryForObject(sql, param, rowMapper())` 이 코드에서 에러가 발생하여 빈 `Optional` 을 반환하게 됩니다. 

~~~ java
try {
    HashMap<String, Object> param = new HashMap<>();
    param.put("scheduleId", scheduleId);
    Schedule schedule = template.queryForObject(sql, param, rowMapper());
    return Optional.of(schedule);
} catch (EmptyResultDataAccessException e) {
    return Optional.empty();
}
~~~

여기 까지는 문제가 되지 않지만 해당 메서드의 반환값을 사용하는 곳에서 문제가 발생합니다.

아래 코드는 단순히 `.get()` 을 통해 `Optional` 내부의 값을 가져오고 있습니다.

~~~ java
Optional<Schedule> byId = findById(scheduleId);
        verifyPassword(byId.get(), password);
~~~

이렇게 단순히 값을 가져오고 내부에서 `Optional.empty()` 가 반환되고 그 값에 접근하게 되면 `NoSuchElementException` 이 발생하게 됩니다.

때문에 안전한 `Optional` 처리가 필요합니다.

### 해결방법

~~~ java
return repository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundException("Requested ID not found"));
~~~

`.orElseThrow` 메서드를 통해 내부에 값이 존재할 경우 해당 값을 꺼내오고 값이 없는 경우에 지정된 예외를 던지도록 수정하였습니다.

이렇게 안전하게 `Optional` 값을 처리하게 되면 공통 예외 처리를 통해 클라이언트에게 어떤 문제가 발생하였는지 깔끔하게 전달할 수 있습니다.

<br>
