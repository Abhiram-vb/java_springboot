FROM openjdk:17
VOLUME /tmp
ADD springlearning-0.0.1-snapshot.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
