FROM openjdk:8

ADD target/spring-boot-0.0.2-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8080