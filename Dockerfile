# Use a JDK 21 image to build
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copy everything and give permission
COPY . .
RUN chmod +x mvnw

# Build the Spring Boot application
RUN ./mvnw clean package -DskipTests

# Use a slim runtime image
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
