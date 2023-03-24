FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
MAINTAINER roydgar
COPY target/eurekaregistry-*.jar eurekaregistry.jar
ENTRYPOINT ["java","-jar","/eurekaregistry.jar"]