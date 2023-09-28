FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY gradlew .
COPY gradle/wrapper/gradle-wrapper.properties ./gradle/wrapper/
COPY gradle/wrapper/gradle-wrapper.jar ./gradle/wrapper/
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x ./gradlew

COPY src ./src
RUN ./gradlew clean build

COPY build/libs/medicalHistory-backend-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "medicalHistory-backend-0.0.1-SNAPSHOT.jar"]