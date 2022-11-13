#!/bin/bash
docker pull namuhuchutong/rollingpaper
docker run --name rollingpaper --env-file ./my_env -v /root/rollingpaper.json:/root/rollingpaper.json -p 8080:8080 namuhuchutong/rollingpaper $AGENT_ID $AGENT_NAME