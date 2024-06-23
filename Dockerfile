FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:11
EXPOSE 3000
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/advert-docker.jar
RUN mkdir /app/examples
COPY ./src/main/resources/examples/* /app/examples
ENTRYPOINT ["java","-jar","/app/advert-docker.jar"]
