FROM public.ecr.aws/lambda/java:11

WORKDIR /var/task

COPY build/libs/app.jar .

ENTRYPOINT ["java", "-jar", "app.jar"]