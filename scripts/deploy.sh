#!/bin/bash
FILE_NAME=`ls ggeu-jeog-ggeu-jeog.jar`
nohup java -javaagent:/root/pinpoint-agent-2.4.2/pinpoint-bootstrap-2.4.2.jar -Dpinpoint.agentId=$AGENT_ID -Dpinpoint.applicationName=$AGENT_NAME -jar $FILE_NAME > ggeu.out 2> ggeu.err < /dev/null &