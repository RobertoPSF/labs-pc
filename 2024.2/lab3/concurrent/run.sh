#!/bin/bash

BASE_DIR=$(dirname -- "$(readlink -f -- "$0")")

if [ "$#" -ne 3 ]; then
    echo "Uso: $0 <num_producers> <num_consumers> <max_items>"
    exit 1
fi

PRODUCERS=$1
CONSUMERS=$2
MAX_ITEMS=$3

java -cp "$BASE_DIR/java/bin" Main "$PRODUCERS" "$CONSUMERS" "$MAX_ITEMS"
