package com.biel.alchemywars.communication.utils;

import lombok.*;

import java.net.*;

public class DockerServiceResolver {
    private static final String STACK_NAME = "aw";

    @Getter
    public enum DockerService {
        SERVER("aw-server", 4500), PROXY_CLUSTER("aw-bungeecord-plugin", 5000, 5010), LOBBY_SERVER_CLUSTER("aw-lobby-plugin", 5100, 5099), GAME_SERVER_CUSTER("aw-gameserver-plugin", 5200, 5299),
        RABBIT_MQ("rabbitmq", 5672);
        private final String name;
        private final Integer startPort;
        private final Integer endPort;
        private String resolvedAddress;

        DockerService(String name, Integer startPort, Integer endPort) {
            this.name = name;
            this.startPort = startPort;
            this.endPort = endPort;
        }

        DockerService(String name, Integer startPort) {
            this(name, startPort, startPort);
        }

        public String getResolvedAddress() {
            if (resolvedAddress == null)
                try {
                    resolvedAddress = InetAddress.getByName(STACK_NAME + "_" + getName()).getHostAddress();
                } catch (UnknownHostException e) {
                    System.out.println("Could not resolve docker service: " + getName());
                    e.printStackTrace();
                }
            return resolvedAddress;
        }

        public URL getHttpURL(String resource) {
            try {
                return new URL("http", getResolvedAddress(), getStartPort(), resource);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public URL getHttpURL() {
            return getHttpURL("");
        }
    }
}
