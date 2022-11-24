## How to run on Local

- edit application.yml

    ```java
    # local 실행 시 주석 지우고 실행
    #  profiles:
    #    default: local
    ```


## How to run on Docker Container

- ./gradlew clean build
- docker-compose up -d —build