
FROM tomcat:9-jre8-alpine

ENV HOME = /usr/local/tomcat/webapps

EXPOSE 8080

RUN rm -rf $HOME/*

COPY ./target/sample.jar $HOME/ROOT.jar

COPY ./mysql-connector-java-8.0.18.jar /usr/local/tomcat/webapps/WEB-INF/lib

WORKDIR $HOME/

ENTRYPOINT ["java"]

CMD ["-jar","ROOT.jar"]
