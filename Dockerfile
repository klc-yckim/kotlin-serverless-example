FROM public.ecr.aws/lambda/java:11

WORKDIR /var/task

COPY build/libs/ktor-kotless-serverless-lambda-all.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]