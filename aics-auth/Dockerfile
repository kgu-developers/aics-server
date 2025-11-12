FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY aics-auth/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]