#!/bin/bash

# Pull new changes
git pull

# Prepare Jar
mvn clean
mvn package -Dmaven.test.skip

# Ensure, that docker-compose stopped
docker-compose stop

# Add environment variables
export BOT_USERNAME=$1
export BOT_TOKEN=$2
export DB_USERNAME='prod_jrtb_db_user'
export DB_PASSWORD='fdsJ3!J532Jf!ds6!234ppf33!4'

# Start new deployment
docker-compose up --build -d