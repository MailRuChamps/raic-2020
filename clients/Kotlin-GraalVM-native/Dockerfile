FROM oracle/graalvm-ce:20.2.0-java11 AS build-aot


RUN gu install native-image
RUN yum install wget -y
RUN wget https://www-eu.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz -P /tmp
RUN tar xf /tmp/apache-maven-3.6.3-bin.tar.gz -C /opt
RUN ln -s /opt/apache-maven-3.6.3 /opt/maven
RUN ln -s /opt/graalvm-ce-java11-20.2.0 /opt/graalvm

ENV JAVA_HOME=/opt/graalvm
ENV M2_HOME=/opt/maven
ENV MAVEN_HOME=/opt/maven
ENV PATH=${M2_HOME}/bin:${PATH}
ENV PATH=${JAVA_HOME}/bin:${PATH}

COPY . /project
WORKDIR /project

RUN mvn package --batch-mode