FROM openjdk:8-jre-alpine
COPY ./CI-CD-assembly-0.1.jar ./
CMD ["java","-jar","./CI-CD-assembly-0.1.jar"]
