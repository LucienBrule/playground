#!/usr/bin/env bash
# This script is used to call the api search endpoint

# Get the search term from the command line
query=$1

# Check if the search term is empty
if [ -z "$query" ]; then
    echo "Please provide a search term"
    exit 1
fi

# Build the request body
request_body=$(cat <<EOF
{
    "query": "$query"
}
EOF
)

# Call the api and pass the request body
# Call the api search endpoint

curl -X POST \
    -H "Content-Type: application/json" \
    -d "$request_body" \
    http://localhost:9001/api/search


echo $RESULTS
