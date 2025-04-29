# Use official Maven image to build the project
FROM maven:3.8.5-openjdk-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy all project files into the container
COPY . .

# Build the project and skip tests if needed
RUN mvn clean package -DskipTests

# ------------------------------
# Use a smaller runtime image
FROM openjdk:17-jdk-slim

# Set working directory in the runtime container
WORKDIR /app

# Copy the built JAR from the builder image
COPY --from=build /app/target/Weather-DashBoard-0.0.1-SNAPSHOT.jar app.jar

# Run the JAR file
CMD ["java", "-jar", "app.jar"]
