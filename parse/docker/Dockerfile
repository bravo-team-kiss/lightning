FROM debian:bullseye-slim

RUN apt-get update && apt-get install -y curl openjdk-11-jre-headless ant && rm -rf /var/lib/apt/lists/*

RUN apt-get update && apt-get install -y vim && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY lightningparser/ /app

RUN ant

RUN chmod -R 755 /app

ENTRYPOINT ["/app/parseAndUpload.sh"] 
