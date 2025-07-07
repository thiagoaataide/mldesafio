FROM openjdk:21
WORKDIR /app
EXPOSE 8088
COPY ./mercadolivre/target/*.jar ./mercadolivreapp.jar
ENTRYPOINT ["java", "-jar", "mercadolivreapp.jar"]