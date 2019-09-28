FROM openjdk:8-jdk-alpine
COPY ./target/phrasebook-service-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch phrasebook-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","phrasebook-service-0.0.1-SNAPSHOT.jar"]