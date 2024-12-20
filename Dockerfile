FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/AppDux-1.0.0-SNAPSHOT.jar .

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "AppDux-1.0.0-SNAPSHOT.jar"]