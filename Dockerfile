FROM openjdk:21
WORKDIR /app
EXPOSE 8088
COPY ./target/*.jar ./mercadolivreapp.jar
ENTRYPOINT ["java", "-jar", "mercadolivreapp.jar"]