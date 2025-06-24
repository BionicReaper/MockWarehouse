FROM maven:amazoncorretto AS build

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ ./src/
RUN mvn clean package -DskipTests=true


FROM eclipse-temurin:17-jre-ubi9-minimal

WORKDIR /app

RUN useradd -m appuser
COPY --from=build --chown=appuser:appuser target/Warehouse-0.0.1-SNAPSHOT.jar ./app.jar

USER appuser

CMD ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]

