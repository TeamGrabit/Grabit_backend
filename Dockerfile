FROM openjdk:11-jdk

WORKDIR /home/spring

CMD ["./gradlew","build","-Pprofile=prod","-x","test"]

COPY build/libs/*SNAPSHOT.jar /home/spring/grabit_backend.jar

CMD ["java", "-jar", "grabit_backend.jar"]