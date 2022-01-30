FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

# Workspace
WORKDIR /usr/share/project

# ADD .jar under the target from host
# into this image

ADD target/Cars.jar        Cars.jar
ADD target/Cars-tests.jar        Cars-tests.jar
ADD target/libs            libs

# ADD suite files
ADD testsuite.xml                    testsuite.xml
ADD healthcheck.sh                   healthcheck.sh
#ADD health check script


ENTRYPOINT sh healthcheck.sh

