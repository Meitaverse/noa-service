FROM openjdk:8
ENV SPRING_PROFILES_ACTIVE=dev LANG=C.UTF-8 LC_CTYPE=C.UTF-8
EXPOSE  8360
WORKDIR /app
ARG JAR_FILE
ADD target/${JAR_FILE} /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]