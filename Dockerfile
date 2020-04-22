FROM ubuntu:18.04
ARG JAR_FILE=target/messages-parser-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
