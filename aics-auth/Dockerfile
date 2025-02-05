FROM openjdk:17-jdk-slim
WORKDIR /app
COPY aics-auth/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]