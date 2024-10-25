# syntax=docker/dockerfile:1

################################################################################

# Étape pour télécharger les dépendances
FROM eclipse-temurin:22-jdk-jammy AS deps

WORKDIR /build

COPY --chmod=0755 mvnw mvnw
COPY .mvn/ .mvn/

RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 ./mvnw dependency:go-offline -DskipTests

################################################################################

# Étape pour construire l'application
FROM deps AS package

WORKDIR /build

COPY ./src src/
RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    ./mvnw package -DskipTests && \
    mv target/$(./mvnw help:evaluate -Dexpression=project.artifactId -q -DforceStdout)-$(./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout)-all.jar target/app.jar

################################################################################

# Étape finale pour exécuter l'application
FROM eclipse-temurin:22-jre-jammy AS final

# Installation des dépendances pour l'environnement graphique et VNC
RUN apt-get update && apt-get install -y \
    xvfb \
    x11vnc \
    novnc \
    libxrender1 \
    libxtst6 \
    libxi6 \
    && rm -rf /var/lib/apt/lists/*

ENV DISPLAY=:0

ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser

# Copier le fichier jar généré depuis l'étape "package"
COPY --from=package /build/target/app.jar /app/app.jar

# Créer un script de démarrage
RUN echo '#!/bin/sh\n\
Xvfb :99 -ac &\n\
export DISPLAY=:99\n\
x11vnc -forever -usepw -create &\n\
/usr/share/novnc/utils/launch.sh --vnc localhost:5900 --listen 8080 &\n\
exec java -jar /app/app.jar' > /app/start.sh \
    && chmod +x /app/start.sh

USER appuser

EXPOSE 8080 5900

ENTRYPOINT ["/app/start.sh"]