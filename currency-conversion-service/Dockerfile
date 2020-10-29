FROM java:8
EXPOSE 8100:8100
ADD target/currency-conversion-service-0.0.1-SNAPSHOT.jar currency-conversion-service.jar
ENTRYPOINT ["java","-jar","currency-conversion-service.jar"]
