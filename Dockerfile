FROM openjdk:8-jre-alpine
RUN mkdir -p /opt/app
WORKDIR /opt/app
COPY ./CI-CD-assembly-0.1.jar ./
CMD ["java","-jar","/CI-CD-assembly-0.1.jar"]
