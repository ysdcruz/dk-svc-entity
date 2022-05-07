FROM openjdk:18-alpine
EXPOSE 3003
ADD target/spring-boot-entity.jar spring-boot-entity.jar
ENTRYPOINT ["java","-jar","spring-boot-entity.jar"]