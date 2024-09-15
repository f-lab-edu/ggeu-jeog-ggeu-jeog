#!/bin/bash
PROC='ps aux | grep ggeu'
if [[ $PROC == *"ggeu"* ]]; then
    echo "Process is running."
    kill -9 $(ps -ef | grep ggeu | grep -v grep | awk '{print $2}')
    echo "removed"
else
    echo "Process is not running."
fi