#!/bin/bash
set -e

APP_HOME=$(pwd)/backend

echo "===== Loading Docker images ====="
sudo docker load -i $APP_HOME/tar/aics-api.tar
sudo docker load -i $APP_HOME/tar/aics-admin.tar
sudo docker load -i $APP_HOME/tar/aics-auth.tar

echo "===== Stopping existing containers ====="
sudo docker compose -f $APP_HOME/docker-compose.yml down || true

echo "===== Starting containers ====="
sudo docker compose -f $APP_HOME/docker-compose.yml --env-file $APP_HOME/.env up -d

echo "===== Deployment finished ====="
