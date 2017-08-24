#!/usr/bin/env bash
export SERVER_PORT=$(./provisioning/port-detection.sh 5100 5199)
./provisioning/render-file.sh server.properties
