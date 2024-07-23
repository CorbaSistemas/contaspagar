FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/contas-pagar-0.0.1-SNAPSHOT.jar contas-pagar.jar
ENTRYPOINT ["java","-jar","/contas-pagar.jar"]
