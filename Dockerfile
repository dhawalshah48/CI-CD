FROM openjdk:8-jre-alpine
COPY ./CI-CD-assembly-0.1.jar ./
ENTRYPOINT ["java","-jar","./CI-CD-assembly-0.1.jar"]
