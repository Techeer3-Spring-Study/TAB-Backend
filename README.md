## How to run on Local

- edit application.yml

    ```java
    spring:
      profiles:
        include:
     #     - docker
          - local
          - s3
    ```


## How to run on Docker Container

- ./gradlew clean build
- docker-compose up -d —build

## How to run docker-compose.prod.yml
- docker-compose -f docker-compose.prod.yml up -d --build

## Swagger url
* http://localhost:8080/swagger-ui/index.html
