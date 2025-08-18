#!/bin/bash
set -e

APP_HOME=$(pwd)/backend

echo "===== Loading Docker images ====="
sudo docker load -i $APP_HOME/tar/aics-api.tar
sudo docker tag aics-admin:${GITHUB_SHA} aics-admin:latest
sudo docker load -i $APP_HOME/tar/aics-admin.tar
sudo docker tag aics-auth:${GITHUB_SHA} aics-auth:latest
sudo docker load -i $APP_HOME/tar/aics-auth.tar
sudo docker tag aics-api:${GITHUB_SHA} aics-api:latest

echo "===== Stopping existing containers ====="
sudo docker compose -f $APP_HOME/docker-compose.yml down || true

echo "===== Starting containers ====="
sudo docker compose -f $APP_HOME/docker-compose.yml --env-file $APP_HOME/.env up -d

echo "===== Deployment finished ====="
