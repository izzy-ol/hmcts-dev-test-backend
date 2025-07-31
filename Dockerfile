FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy everything EXCEPT gradlew first
COPY gradle gradle
COPY build.gradle build.gradle
COPY src src
COPY .env .env

# Now copy gradlew separately and fix it
COPY gradlew gradlew
COPY gradlew.bat gradlew.bat
RUN sed -i 's/\r$//' gradlew && chmod +x gradlew

# Run the build
RUN ./gradlew build -x test

# Run the app
CMD ["java", "-jar", "build/libs/test-backend.jar"]
