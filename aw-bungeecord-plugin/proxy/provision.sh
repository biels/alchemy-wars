#!/usr/bin/env bash
export PROXY_PORT=5000 #If not def for multi proxy
./provisioning/render.sh config.yml.template > config.yml
