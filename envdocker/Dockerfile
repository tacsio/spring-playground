FROM openjdk:11.0
EXPOSE 8080
COPY ./target/*.jar app.jar
ENV ENV2="from dockerfile"
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
