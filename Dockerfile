FROM openjdk:18-alpine

LABEL Author="Sebastian Regucki, Maciej Dudziak"

ADD target/demo-0.0.1-SNAPSHOT.jar.original /app.jar

EXPOSE 8085

CMD ["java","-jar","target/demo-0.0.1-SNAPSHOT.jar.original"]