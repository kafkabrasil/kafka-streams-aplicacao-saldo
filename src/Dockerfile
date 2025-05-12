FROM openjdk:17-jdk-slim

WORKDIR /app
RUN mvn clean package
COPY target/*-with-dependencies.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]
