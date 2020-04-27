FROM openjdk:8-jre-alpine
RUN mkdir -p /opt/app
WORKDIR /opt/app
COPY ./CI-CD-assembly-0.1.jar ./target/scala-2.12/
CMD ["java","-jar","/target/scala-2.12/CI-CD-assembly-0.1.jar"]
