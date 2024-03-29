FROM openjdk:11-ea-16-jre-slim AS builder

WORKDIR application
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:11-ea-16-jre-slim
WORKDIR application

COPY --from=builder application/dependencies/ ./
RUN true

COPY --from=builder application/spring-boot-loader/ ./
RUN true

COPY --from=builder application/snapshot-dependencies/ ./
RUN true

COPY --from=builder application/application/ ./
RUN true

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
