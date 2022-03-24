FROM adoptopenjdk/openjdk11

EXPOSE 8080

ADD ./build/libs/chirp-hello-world-1.0.0.jar chirp.jar

ENTRYPOINT ["java", "-jar", "chirp.jar"]
