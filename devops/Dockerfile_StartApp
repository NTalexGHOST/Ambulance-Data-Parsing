# docker build . -t spring-boot:v1
# docker run -d -v ambulance_app:/ambulance_app -p 8080:8080 --name Ambulance_APP spring-boot:v1

FROM openjdk:17-alpine
EXPOSE 8080
RUN mkdir ambulance_app
ENTRYPOINT ["java","-jar","/ambulance_app/parsingApp-0.0.1-SNAPSHOT.jar"]
