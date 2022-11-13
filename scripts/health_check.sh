#!/bin/bash

echo "---------------- Health Check Start ----------------"

ping -c 3 -w 5 $1

if [ $? -eq 0 ]
then
        echo "---------------- $1 is alive ----------------"
else
        echo "---------------- $1 is dead ----------------"
        exit 1
fi
