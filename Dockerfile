FROM openjdk:jre-alpine
WORKDIR /opt/communicator/
ADD target/communicator.jar /opt/communicator/
CMD ["java","-jar","/opt/communicator/communicator.jar"]
