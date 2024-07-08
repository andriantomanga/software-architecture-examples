FROM openjdk:17-alpine
RUN mkdir /app
COPY target/*.jar /app/booksearchpro.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar /app/booksearchpro.jar --elasticsearch.url=$ELASTICSEARCH_URL"]
