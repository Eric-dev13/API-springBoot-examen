FROM openjdk:17

# Define environment variables
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \JAVA_OPTS=""

# Set the working directory to /app
WORKDIR /app

# Copy the executable into the container at /app
ADD target/*.jar app.war

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Copier le fichier WAR dans le conteneur
# COPY api.mushroom-0.0.1-SNAPSHOT.war /app/api.war

# Commande par défaut à exécuter
CMD ["java", "-jar", "/app/app.war"]