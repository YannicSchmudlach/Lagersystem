FROM eclipse-temurin:21-jdk

WORKDIR /server


COPY target/lagersystem-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]



