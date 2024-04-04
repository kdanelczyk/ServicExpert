FROM openjdk:17-alpine

COPY target/ServicExpert-0.0.1-SNAPSHOT.jar ServicExpert-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "ServicExpert-0.0.1-SNAPSHOT.jar"]