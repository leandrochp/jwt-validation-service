FROM gradle:8.5.0-jdk17 AS BUILD_IMAGE
COPY . /home/source/java
WORKDIR /home/source/java

USER root
RUN chown -R gradle /home/source/java

USER gradle
RUN gradle clean build

FROM openjdk:17-alpine

ENV APPLICATION_USER spring
RUN adduser -D -g '' $APPLICATION_USER

RUN mkdir /app
RUN chown -R $APPLICATION_USER /app

USER $APPLICATION_USER

COPY --from=BUILD_IMAGE /home/source/java/build/libs/jwt-validation-service-1.0.0.jar /app/jwt-validation-service-1.0.0.jar
WORKDIR /app

CMD ["java", "-jar", "jwt-validation-service-1.0.0.jar"]
