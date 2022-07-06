# docker build . -t compile-jar:v1
# docker run -it -v /home/user/ambulance_data_app/server:/Ambulance-Data-Parsing/target -v /home/user/Ambulance-Data-Parsing/:/Ambulance-Data-Parsing --name compile-jar compile-jar

FROM openjdk:17-alpine
RUN mkdir Ambulance-Data-Parsing
WORKDIR /Ambulance-Data-Parsing
ENTRYPOINT ["/bin/sh","/Ambulance-Data-Parsing/mvnw","package","-Dmaven.test.skip"]
