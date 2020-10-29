FROM java:8
EXPOSE 8000:8000
ADD target/currency-exchange-service-0.0.1-SNAPSHOT.jar currency-exchange-service.jar
ENTRYPOINT ["java","-jar","currency-exchange-service.jar"]