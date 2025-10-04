# Dockerfile
FROM openjdk:21-ea-24-oracle

WORKDIR /app

# aquí debemos asegurarnos del nombre de nuestro jar coincida
COPY target/vetcore-0.0.1-SNAPSHOT.jar app.jar

# ubicación y nombre del wallet descomprimido
COPY src/main/resources/wallet /app/oracle_wallet/

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]