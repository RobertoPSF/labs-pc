#!/bin/bash

if [ "$#" -ne 2 ]; then
    echo "Use: $0 <num_producers> <num_consumers>"
    exit 1
fi

PRODUCERS=$1
CONSUMERS=$2

java -cp src/java/bin src.Main $PRODUCERS $CONSUMERS
