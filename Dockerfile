#이미지를 만들어줌
FROM openjdk:11-jre-slim
COPY build/libs/group-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","./app.jar"]