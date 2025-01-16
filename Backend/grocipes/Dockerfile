# Używamy oficjalnego obrazu Javy
FROM openjdk:17-jdk-slim

# Ustawiamy zmienną środowiskową do wskazania, w którym katalogu aplikacja będzie uruchamiana
WORKDIR /app

# Kopiujemy plik jar z aplikacji backendowej do kontenera
COPY target/grocipes-0.0.1-SNAPSHOT.jar /app/grocipes-0.0.1-SNAPSHOT.jar

# Ustawiamy domyślny port
EXPOSE 8080

# Komenda uruchamiająca aplikację Spring Boot
ENTRYPOINT ["java", "-jar", "grocipes-0.0.1-SNAPSHOT.jar"]