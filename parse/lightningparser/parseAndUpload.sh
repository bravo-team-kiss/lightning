#!/bin/bash

TOKEN='OwTn1fjNEvmbt8uOx5SyUUUMJQs7iqSRORNxPj7vueJz7rO8qfd6-DKl3o18vsD_Y0Y7ym2CgwRlnKcFkNfHOA=='
java -cp build/classes lightningparser.LightningParser > body.txt

curl 'http://localhost:8086/api/v2/write?org=teamkiss&bucket=test&precision=ms' \
--header "Authorization: Token $TOKEN" --header "Content-Type: text/plain" --request POST $@ --data-binary "@body.txt"
