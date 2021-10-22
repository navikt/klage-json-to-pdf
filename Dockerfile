FROM navikt/java:11

COPY build/libs/*.jar ./

EXPOSE 5005

ENV JAVA_TOOL_OPTIONS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
