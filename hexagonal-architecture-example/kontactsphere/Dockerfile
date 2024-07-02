FROM openjdk:17-alpine
RUN mkdir /app
COPY target/*.jar /app/kontactsphere.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/kontactsphere.jar"]