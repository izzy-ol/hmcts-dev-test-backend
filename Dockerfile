FROM eclipse-temurin:21-jdk

WORKDIR /app

#,/gradlew was not found so trying this
COPY gradlew gradlew
RUN chmod +x gradlew

COPY . .

RUN ./gradlew build -x test

CMD ["java", "-jar", "build/libs/hmcts-dev-test-backend-0.0.1.jar"]
