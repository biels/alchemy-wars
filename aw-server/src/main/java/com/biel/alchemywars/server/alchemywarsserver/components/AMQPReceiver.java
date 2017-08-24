package com.biel.alchemywars.server.alchemywarsserver.components;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class AMQPReceiver {
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}
