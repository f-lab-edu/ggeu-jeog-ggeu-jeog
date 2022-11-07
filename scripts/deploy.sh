#!/bin/bash

java -javaagent:/root/pinpoint-agent-2.4.2/pinpoint-bootstrap-2.4.2.jar -Dpinpoint.agentId=$1 -Dpinpoint.applicationName=$2 -jar /root/ggeu-jeog-ggeu-jeog/build/libs/ggeu-jeog-ggeu-jeog.jar