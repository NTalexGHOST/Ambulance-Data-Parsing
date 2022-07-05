# docker build . -t compile-jar:v1
# docker -d -v /home/user/ambulance_data_app/server:/Ambulance-Data-Parsing/target --name Compile-JAR compile-jar:v1

FROM openjdk:17-alpine
RUN mkdir Ambulance-Data-Parsing
ADD /home/user/Ambulance-Data-Parsing /Ambulance-Data-Parsing
WORKDIR /Ambulance-Data-Parsing
ENTRYPOINT ["/bin/sh","/Ambulance-Data-Parsing/mvnw","package","-Dmaven.test.skip=true"