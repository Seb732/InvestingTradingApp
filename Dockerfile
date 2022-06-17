FROM openjdk:18-alpine

ADD target/InvestingTradingApp-0.0.1-SNAPSHOT.jar InvestingTradingApp-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "InvestingTradingApp-0.0.1-SNAPSHOT.jar"]