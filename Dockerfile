FROM openjdk:18-alpine

LABEL Author="Sebastian Regucki, Maciej Dudziak"

RUN echo "Our first Docker image!"

ADD target/demo-0.0.1-SNAPSHOT.jar /app.jar

WORKDIR /opt

CMD java -jar demo-0.0.1-SNAPSHOT