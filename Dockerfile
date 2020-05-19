FROM openjdk:8-jdk-alpine
MAINTAINER Svitlana Fedorchuk "fedrchuksveta@gmail.com"
EXPOSE 8080
WORKDIR /usr/local/bin/
COPY target/forum-1.0-SNAPSHOT.jar forum.jar
CMD ["java", "-jar", "forum.jar"]