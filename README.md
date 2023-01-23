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

## Swagger url
* http://localhost:8080/swagger-ui/index.html