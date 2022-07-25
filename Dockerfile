FROM registry.access.redhat.com/ubi8/openjdk-8:1.13-1.1655306434 as build
RUN mkdir app
COPY . /home/jboss/app/
WORKDIR /home/jboss/app
RUN ./mvnw clean install

FROM  registry.access.redhat.com/ubi8/openjdk-8:1.13-1.1655306434 as run
COPY --from=build /home/jboss/app/target/wc-training-persistence-0.0.1-SNAPSHOT.jar /home/jboss/app.war
ENTRYPOINT ["java", "-jar", "/home/jboss/app.war"]

