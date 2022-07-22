#!/bin/bash

echo "Influx Hostname: $1"
echo "Input File: $2"

java -cp build lightningparser.LightningParser $2 > body.txt
EXIT_CODE=$?
if [ "$EXIT_CODE" != "0" ]; then
  echo "Java exited with error code $EXIT_CODE"
  exit $EXIT_CODE
fi

curl "http://$1:8086/api/v2/write?org=team-kiss&bucket=sensordata&precision=ms" \
--header "Authorization: Token $INFLUX_TOKEN" --header "Content-Type: text/plain" --request POST --data-binary "@body.txt"
