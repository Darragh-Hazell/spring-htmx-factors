FROM maven:3.9-eclipse-temurin-21-alpine AS build

COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-alpine AS application

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]