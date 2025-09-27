# Stage 1: Build ứng dụng bằng Maven
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml và tải dependencies trước (cache để lần sau build nhanh hơn)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code rồi build jar
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime với JDK 21
FROM eclipse-temurin:21-jdk AS runtime
WORKDIR /app

# Copy jar từ stage build
COPY --from=build /app/target/zalo_manager-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
