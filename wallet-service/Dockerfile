FROM openjdk:8-jdk-alpine
MAINTAINER Bovas Chinnathambi <bovas1990@gmail.com>
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 2222