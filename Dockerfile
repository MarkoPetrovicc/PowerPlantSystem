FROM openjdk:11
EXPOSE 8080
COPY target/power-plant.jar power-plant.jar
ENTRYPOINT ["java","-jar","/power-plant.jar"]