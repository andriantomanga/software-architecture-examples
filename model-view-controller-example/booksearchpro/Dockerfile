FROM openjdk:17-alpine
RUN mkdir /app
COPY target/*.jar /app/booksearchpro.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/booksearchpro.jar"]

