FROM adoptopenjdk:11-jdk-openj9 as build
WORKDIR /workspace/app
COPY ./gradle gradle
COPY build.gradle settings.gradle gradlew ./
COPY src src
RUN ./gradlew bootJar

FROM adoptopenjdk:11-jre-openj9
COPY --from=build /workspace/app/build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
