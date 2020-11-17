#compile
FROM maven:3.6.3-openjdk-11 as build
WORKDIR /compile
COPY . /compile
RUN mvn clean package

#package
FROM adoptopenjdk:11-hotspot as package
WORKDIR /app
COPY --from=build /target/*.jar .
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract


#production environment
FROM adoptopenjdk:11-hotspot
WORKDIR /app
COPY --from=package app/dependencies/ ./
COPY --from=package app/spring-boot-loader/ ./
COPY --from=package app/snapshot-dependencie/ ./
COPY --from=package app/application/ ./

EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","org.springframework.boot.loader.JarLauncher"]
