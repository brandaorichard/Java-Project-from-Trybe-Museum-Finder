# First stage: build-image
FROM maven:3-openjdk-17 AS build-image

# Working directory
WORKDIR /to-build-app

# Copy the pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the project files
COPY src ./src

# Build the JAR package without running tests
RUN mvn package -DskipTests

# Second stage: runtime
FROM eclipse-temurin:17-jre-alpine

# Working directory
WORKDIR /app

# Copy the JAR from the build stage to the final stage
COPY --from=build-image /to-build-app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Set the entry point
ENTRYPOINT ["java", "-jar", "app.jar"]
