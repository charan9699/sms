# First stage: build the application
FROM maven:3.8.6-jdk-8 AS build
COPY . /app
WORKDIR /app
RUN keytool -noprompt -importcert -trustcacerts -alias mavencert -file cacert.pem -keystore /usr/local/openjdk-8/jre/lib/security/cacerts -storepass changeit
RUN mvn install -DskipTests=true -Djavax.net.ssl.trustStorePassword=changeit

# Second stage: create a slim image
FROM openjdk:8-jre-slim
COPY --from=build /app/target/sms.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
