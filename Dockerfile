FROM openjdk:17
WORKDIR /app
COPY ./target/user-service.jar /app
EXPOSE 9191
CMD ["java","-jar","user-service.jar"]