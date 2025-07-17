FROM maven:3.8.4-openjdk-21 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/order-query-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
