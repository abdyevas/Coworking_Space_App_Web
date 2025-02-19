FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/coworking-reservation-system-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8005

CMD ["java", "-jar", "app.jar"]
