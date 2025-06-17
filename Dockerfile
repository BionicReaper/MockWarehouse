FROM maven:amazoncorretto AS build

COPY src/ ./src/
COPY pom.xml .

RUN mvn dependency:go-offline && \
    mvn clean package -DskipTests=true


FROM eclipse-temurin:17-jre-ubi9-minimal

WORKDIR /app

COPY --from=build target/Warehouse-0.0.1-SNAPSHOT.jar ./app.jar
RUN useradd -m appuser && \
    chown appuser:appuser ./app.jar

USER appuser

CMD ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]

