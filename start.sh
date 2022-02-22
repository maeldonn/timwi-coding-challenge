#!/bin/bash

# Ensure, that docker-compose stopped
docker-compose stop

# Add environment variables
export DB_HOST='postgres'
export DB_PORT='5432'
export DB_NAME='postgres'
export DB_USERNAME='postgres'
export DB_PASSWORD='password'
export SPOTIFY_TOKEN='YOUR_TOKEN_HERE'

# Start new deployment
docker-compose up --build -d