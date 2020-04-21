FROM openjdk:8-jre-alpine
COPY ./target/scala-2.12/CI-CD-assembly-0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar", "Task1"]
