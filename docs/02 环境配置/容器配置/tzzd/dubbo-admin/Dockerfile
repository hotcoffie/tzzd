FROM openjdk:8

MAINTAINER labwinner.com<hotcoffie@163.com>

VOLUME /tmp

ADD dubbo-admin-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
