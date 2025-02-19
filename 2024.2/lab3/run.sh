#!/bin/bash

BASE_DIR=$(dirname -- "$( readlink -f -- "$0"; )")

if [ "$#" -ne 2 ]; then
    echo "Use: $0 <num_producers> <num_consumers> <max_itens>"
    exit 1
fi

PRODUCERS=$1
CONSUMERS=$2
MAX_ITENS=$3

java -cp $BASE_DIR/java/bin Main $PRODUCERS $CONSUMERS $MAX_ITENS
