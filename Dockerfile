# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy pom.xml
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Production stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Install dumb-init to handle signals properly
RUN apk add --no-cache dumb-init

# Copy built JAR from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Create a non-root user
RUN addgroup -g 1001 -S appuser
RUN adduser -S appuser -u 1001
USER appuser

# Expose port
EXPOSE 8081

# Use dumb-init to handle signals
ENTRYPOINT ["dumb-init", "--"]

# Start the application
CMD ["java", "-jar", "app.jar"]
