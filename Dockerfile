FROM openjdk:8
ADD target/users-application.jar users-application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "users-application.jar"]
