#!/usr/bin/env bash
function EPHEMERAL_PORT(){
    LPORT=$1;
    UPORT=$2;
    while true; do
        MPORT=$[$LPORT + ($RANDOM % ($UPORT - $LPORT))];
        (echo "" >/dev/tcp/127.0.0.1/${MPORT}) >/dev/null 2>&1
        if [ $? -ne 0 ]; then
            echo $MPORT;
            return 0;
        fi
    done
}
EPHEMERAL_PORT $1 $2