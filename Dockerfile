FROM adoptopenjdk/openjdk11
MAINTAINER alitahir14@outlook.com
ADD target/assignment-0.0.1-SNAPSHOT.jar assignment.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","assignment.jar"]