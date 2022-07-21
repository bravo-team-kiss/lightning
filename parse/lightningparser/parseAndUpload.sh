#!/bin/bash

echo $1 

java -cp build/classes lightningparser.LightningParser $2 > body.txt

curl "http://'$1':8086/api/v2/write?org=teamkiss&bucket=test&precision=ms" \
--header "Authorization: Token $INFLUX_TOKEN" --header "Content-Type: text/plain" --request POST --data-binary "@body.txt"
