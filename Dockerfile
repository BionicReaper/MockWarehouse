FROM maven:amazoncorretto AS build

COPY src/ ./src/
COPY src/main/resources/docker-application.yml ./src/main/resources/application.yml
COPY pom.xml .

RUN mvn clean package -DskipTests=true


FROM eclipse-temurin:17-jre-ubi9-minimal

WORKDIR /app

RUN useradd -m appuser

COPY --from=build target/Warehouse-0.0.1-SNAPSHOT.jar ./app.jar
RUN chown appuser:appuser ./app.jar

USER appuser

CMD ["java", "-jar", "app.jar"]

