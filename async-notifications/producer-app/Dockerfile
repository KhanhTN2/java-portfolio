FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /workspace
COPY . .
RUN mvn -q -DskipTests=true -DskipITs=true -pl async-notifications/producer-app -am package

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /workspace/async-notifications/producer-app/target/producer-app-*.jar /app/app.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","/app/app.jar"]
