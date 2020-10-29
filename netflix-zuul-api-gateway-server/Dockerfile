FROM java:8
EXPOSE 8765:8765
ADD target/netflix-zuul-api-gateway-server-0.0.1-SNAPSHOT.jar netflix-zuul-api-gateway-server.jar
ENTRYPOINT ["java","-jar","netflix-zuul-api-gateway-server.jar"]