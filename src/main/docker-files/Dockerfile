FROM openjdk:18-alpine
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} spring-boot-user.jar

# A few reasons for installing distribution-provided OpenJDK:
#
#  1. Oracle.  Licensing prevents us from redistributing the official JDK.
#
#  2. Compiling OpenJDK also requires the JDK to be installed, and it gets
#     really hairy.
#
#     For some updatePaymentDetails build times, see Debian's buildd logs:
#       https://buildd.debian.org/status/logs.php?pkg=openjdk-8

#MAINTAINER Alberto Naperi Jr. <albertonaperijr@gmail.com>

#RUN apk --update add curl bash openssh && \
#	rm -rf /var/cache/apk/*

# Default to UTF-8 file.encoding
#ENV LANG C.UTF-8

#ENV JAVA_OPTS ""

#ENV APP_JAR_NAME dk-svc-payment
#ENV APP_JAR_VERSION 2.0.0

# ENV SPRING_BOOT_REPO_FOLDER="/opt/repos/spring-boot"
#ENV SPRING_BOOT_JAR_FILE_NAME="$APP_JAR_NAME-$APP_JAR_VERSION.jar"

#RUN mkdir -p /var/www/images/testing/qrcode
#RUN mkdir -p /var/www/images/qrcode

#VOLUME /var/www/images/testing/qrcode
#VOLUME /var/www/images/qrcode

#COPY $SPRING_BOOT_JAR_FILE_NAME $SPRING_BOOT_JAR_FILE_NAME

EXPOSE 8050

#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /$SPRING_BOOT_JAR_FILE_NAME" ]

ENTRYPOINT ["java","-jar","spring-boot-user.jar"]