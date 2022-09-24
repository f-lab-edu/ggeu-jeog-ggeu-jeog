FROM openjdk:11-jdk
ARG JAR_FILE=build/libs/ggeu-jeog-ggeu-jeog.jar
VOLUME /tmp
COPY ${JAR_FILE} ggeu-jeog-ggeu-jeog.jar
ENTRYPOINT ["java", "-jar", "ggeu-jeog-ggeu-jeog.jar"]