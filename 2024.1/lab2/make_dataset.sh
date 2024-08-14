#!/bin/bash

# generate $nfiles large files in a dataset directory
nfiles=$1
file_size=$2

if [ -z "$nfiles" ] || [ -z "$file_size" ]; then
    echo "Usage: $0 <number_of_files> <file_size_in_MB>"
    exit 1
fi

# Convert file size to bytes
file_size_bytes=$((file_size * 1024 * 1024))

mkdir -p dataset

for i in $(seq 1 $nfiles); do
    # Generate a large file with the specified size
    dd if=/dev/urandom of=dataset/file.$i bs=1M count=$file_size
done
