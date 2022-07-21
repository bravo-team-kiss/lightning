FROM openjdk:slim

RUN apt-get update && apt-get install -y curl

WORKDIR /app

COPY lightningparser/ /app

RUN chmod -R 755 /app

ENTRYPOINT ["/app/parseAndUpload.sh"] 
