FROM openjdk:slim

WORKDIR /app

COPY lightningparser/ /app

RUN chmod -R 755 /app

ENTRYPOINT ["/app/parseAndUpload.sh"] 
