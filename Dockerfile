FROM namuhuchutong/alpine-openjdk11-pinpoint:latest
MAINTAINER namuhuchutong
ARG JAR_FILE=build/libs/ggeu-jeog-ggeu-jeog.jar
VOLUME /tmp
COPY ${JAR_FILE} ggeu-jeog-ggeu-jeog.jar
COPY scripts/deploy.sh /
RUN ["chmod", "755", "/deploy.sh"]
ENTRYPOINT ["/deploy.sh"]