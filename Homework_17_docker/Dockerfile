FROM openjdk:8-jdk-alpine
COPY target/library.jar library.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./library.jar"]