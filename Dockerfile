FROM registry.access.redhat.com/ubi8/ubi:latest

VOLUME ["/opt/ticket"]

ARG JarName=rest-0.0.1

COPY target/$JarName.jar /opt/ticket/$JarName.jar

RUN dnf -y install java-17-openjdk
 
ENV RunJarName=$JarName

WORKDIR /opt/ticket

CMD java -jar /opt/ticket/$RunJarName.jar