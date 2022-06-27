## 프로젝트 실행

![](post/1.png)

프로젝트를 실행을 하기 위해서는 `Active Profile` 을 `mysql` 로 넣어 주어야 한다.

## MySql

1. 도커 파일 build

프로젝트의 Root Path 에서 Dockerfile 을 빌드 한다.

```
docker build -t mysql_custom:laster ./docker/.
```

2. 도커 이미지 컨테이너로 Run

```
docker run --name mysql_custom -d -p 3306:3306 mysql_custom:laster
```