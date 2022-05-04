FROM openjdk:18-alpine
EXPOSE 3003
ADD target/spring-boot-entity.jar spring-boot-entity.jar
ENTRYPOINT ["java","-jar","spring-boot-entity.jar"]
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /$SPRING_BOOT_JAR_FILE_NAME" ]
