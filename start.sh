#!/bin/bash

# Ensure, that docker-compose stopped
docker-compose stop

# Add environment variables
export DB_HOST='postgres'
export DB_PORT='5432'
export DB_NAME='postgres'
export DB_USERNAME='postgres'
export DB_PASSWORD='password'
export SPOTIFY_TOKEN='BQANe8HU9B6-24eNPw_oGXajTUuZ43We4fhehDSKIwR45cvo5rT4cfNsuAdjQ-KcErQERLAgiN_fr45FtMj6OgFtiMT_tZQ1SSn0sd6s164H7WyF-bJ5Vsszfj-UCfJ0--aW27cI5hiXYn32rW2r9m4Gla_viC20bwc'

# Start new deployment
docker-compose up --build -d