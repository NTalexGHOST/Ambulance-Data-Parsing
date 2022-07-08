# docker build . -t compile-jar

FROM openjdk:17-alpine
RUN mkdir Ambulance-Data-Parsing
WORKDIR /Ambulance-Data-Parsing
ENTRYPOINT ["/bin/sh","/Ambulance-Data-Parsing/mvnw","package","-Dmaven.test.skip"]
