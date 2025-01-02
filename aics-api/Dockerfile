FROM openjdk:17-jdk-slim
WORKDIR /app
COPY aics-api/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]