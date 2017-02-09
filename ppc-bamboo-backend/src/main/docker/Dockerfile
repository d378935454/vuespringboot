FROM openjdk:8-jre-alpine

ENV PPC_SLEEP 0

# add directly the war
ADD *.war /app.war

RUN sh -c 'touch /app.war'
VOLUME /tmp
EXPOSE 8081
CMD echo "The application will start in ${PPC_SLEEP}s..." && \
    sleep ${PPC_SLEEP} && \
    java -Djava.security.egd=file:/dev/./urandom -jar /app.war
