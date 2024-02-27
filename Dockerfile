FROM tomcat:latest

LABEL maintainer="Nidhi Gupta"

COPY ./target/api.mushroom-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

# Ajouter la commande chmod pour ajuster les permissions
RUN chmod 644 /usr/local/tomcat/webapps/api.mushroom-0.0.1-SNAPSHOT.war

CMD ["catalina.sh", "run"]
