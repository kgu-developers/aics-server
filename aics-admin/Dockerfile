FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY aics-admin/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]