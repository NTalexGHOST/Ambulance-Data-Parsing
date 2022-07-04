FROM openjdk:17-alpine
EXPOSE 8080
RUN mkdir Ambulance-Data-Parsing
ADD Ambulance-Data-Parsing /Ambulance-Data-Parsing 
RUN cd Ambulance-Data-Parsing \
	&& apk update \
	&& apk add git \
	&& git init \
	&& git checkout lab \
	&& sh mvnw package
ENTRYPOINT ["java","-jar","/Ambulance-Data-Parsing/taget/parsingApp-0.0.1-SNAPSHOT.jar"]