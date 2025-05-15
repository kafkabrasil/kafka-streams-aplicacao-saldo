FROM maven:3.8.3-openjdk-17 as builder

WORKDIR /app
COPY . .
RUN mvn clean package

FROM amazoncorretto:17-alpine-jdk as runtime

WORKDIR /app
COPY --from=builder /app/target/*-with-dependencies.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]
