FROM openjdk:18-alpine

LABEL Author="Sebastian Regucki, Maciej Dudziak"

ADD target/maven-status/demo-0.0.1-SNAPSHOT.jar target/maven-status/demo-0.0.1-SNAPSHOT.jar

CMD ["java","-jar","target/maven-status/demo-0.0.1-SNAPSHOT.jar"]