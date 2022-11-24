## How to run on Local

- edit application.yml

    ```java
    spring:
      datasource:
        include:
    #      - docker
          - local
    ```


## How to run on Docker Container

- ./gradlew clean build
- docker-compose up -d —build