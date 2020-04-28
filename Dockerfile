# syntax=docker/dockerfile:experimental
FROM maven:3.6.3-openjdk-14 AS build

ARG MODULE
USER root

COPY ./ ./src
WORKDIR ./src

RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests=true -pl :$MODULE -am
RUN cp ./$MODULE/target/$MODULE-0.0.1-SNAPSHOT.jar ../$MODULE-0.0.1-SNAPSHOT.jar
WORKDIR ../
RUN rm -rf ./src

FROM openjdk:14-jdk-slim

ARG MODULE
ENV NAME $MODULE
COPY --from=build $MODULE-0.0.1-SNAPSHOT.jar $MODULE.jar

ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar $NAME.jar
