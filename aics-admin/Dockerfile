FROM openjdk:17-jdk-slim
WORKDIR /app
COPY aics-admin/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]