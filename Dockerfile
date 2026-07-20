#build stage
FROM maven:3.9.11-eclipse-temurin-17 AS build

WORKDIR /build

COPY pom.xml .

RUN  mvn dependency:go-offline
COPY src ./src



RUN mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:17

WORKDIR /app

COPY --from=build /build/target/lagersystem-*.jar /app/

EXPOSE 8080


CMD java -jar  lagersystem-0.0.1-SNAPSHOT.jar
