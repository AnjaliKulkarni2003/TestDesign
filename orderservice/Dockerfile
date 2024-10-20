# REPLACE with the version that you want to use. Make sure it is consistent with other locations
# where we specify this version number.
FROM org.github:40100/library/openjdk:17-jdk
MAINTAINER anjali

#USER root
#WORKDIR /app

ADD target/app.jar app.jar
#USER nca

#CMD /usr/bin/java -jar app.jar
# JVM arguments to open specific modules
ENV JAVA_OPTS="--add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED"

ENTRYPOINT ["java", "-jar", "app.jar"]

# Expose ports
EXPOSE 8081
