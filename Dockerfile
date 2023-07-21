FROM openjdk:11

RUN mkdir -p /backend/config/

COPY target/task-0.0.1-SNAPSHOT.jar dataoxscrapingproject.jar
COPY application-deployment.properties /config/
ENV SPRING_CONFIG_LOCATION=file:/config/application-deployment.properties
VOLUME /src/main/resources/application.properties:SPRING_CONFIG_LOCATION

ENTRYPOINT ["java", "-jar", "dataoxscrapingproject.jar"]
