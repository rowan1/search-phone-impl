FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/impl-0.0.1-SNAPSHOT.jar impl-0.0.1-SNAPSHOT.jar
COPY sample.db /sample.db
EXPOSE 8080
ENTRYPOINT ["java","-jar","impl-0.0.1-SNAPSHOT.jar"]