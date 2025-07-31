SHELL = 'bin/bash'
LOCAL_COMPOSE_FILES = -f docker-compose.yml
PROJECT_NAME = hmcts-task-management-api

export COMPOSE_PROJECT_NAME=${PROJECT_NAME}

default: help

up: ## Starts/restarts the api container
    docker compose ${LOCAL_COMPOSE_FILES} down -v
    docker compose ${LOCAL_COMPOSE_FILES} up

down: ## Stops and removes containers
      docker compose ${LOCAL_COMPOSE_FILES} down -v

refresh: ## Stops, removes, and cleans container list
         docker compose ${LOCAL_COMPOSE_FILES} down -v
         docker container prune
         docker ps -a

build: ## Build the container
       docker compose ${LOCAL_COMPOSE_FILES} build --no-cache
