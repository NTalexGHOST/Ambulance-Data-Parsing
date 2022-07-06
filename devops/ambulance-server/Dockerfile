# docker build . -t spring-boot:v1
# docker run -d -v ambulance_app:/ambulance_app -p 8080:8080 --name ambulance_app spring-boot:v1

FROM openjdk:17-alpine
EXPOSE 8080
RUN mkdir target \
	&& mkdir -p src/data
ENTRYPOINT ["java","-jar","/target/parsingApp-0.0.1-SNAPSHOT.jar"]
