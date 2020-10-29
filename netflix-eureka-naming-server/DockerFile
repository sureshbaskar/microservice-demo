FROM java:8
EXPOSE 8761:8761
ADD target/netflix-eureka-naming-server-0.0.1-SNAPSHOT.jar netflix-eureka-naming-server.jar
ENTRYPOINT ["java","-jar","netflix-eureka-naming-server.jar"]