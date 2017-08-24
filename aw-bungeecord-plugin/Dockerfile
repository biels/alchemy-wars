FROM frolvlad/alpine-oraclejdk8:slim
RUN ["apk", "add", "--no-cache", "bash", "perl"]
VOLUME /tmp
ADD target/ /server
EXPOSE 5000-5099
WORKDIR /server
ENTRYPOINT [ "./entrypoint.sh" ]