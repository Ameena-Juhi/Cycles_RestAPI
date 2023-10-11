# Use an official OpenJDK runtime as a parent image

FROM openjdk:17-jdk-alpine

# Copy the JAR file from your host to the container

COPY /target/*.jar app.jar

# Expose the port your Spring Boot application is listening on (default is 8080)

EXPOSE 8080

# Define the command to run your application

ENTRYPOINT ["java", "-jar", "/app.jar"]