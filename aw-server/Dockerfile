FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/aw-server-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 4500
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]