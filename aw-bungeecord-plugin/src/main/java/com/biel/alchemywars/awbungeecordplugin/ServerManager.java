package com.biel.alchemywars.awbungeecordplugin;

import com.biel.alchemywars.communication.protobuf.SpringToBungee.ServerRegistration;
import com.biel.alchemywars.communication.utils.DockerServiceResolver;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ListenerInfo;

import java.net.InetSocketAddress;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ServerManager {
    int lobbyCount = 0;
    ListenerInfo lis;

    public void prepareListener() {
        List<String> collect = IntStream.rangeClosed(1, 1).mapToObj(i -> "lobby-" + i).collect(Collectors.toList());
        lis = new ListenerInfo(new InetSocketAddress("0.0.0.0", 5002), "aw-proxy",
                100, 50, collect, true, new HashMap<>(), "GLOBAL_PING",
                false, false, 5002, true);

        ProxyServer.getInstance().getConfig().getListeners().add(lis);
    }

    public void addServer(int port, ServerRegistration.ServerType serverType) {
        boolean isLobby = serverType == ServerRegistration.ServerType.LOBBY;
        String name = serverType.name().toLowerCase() + "-" + (isLobby ? (lobbyCount + 1) : port);
        InetSocketAddress address = new InetSocketAddress(getServerCluster(serverType).getResolvedAddress(), port);
        ServerManagerUtil.addServer(name, address, name, false);
        ProxyServer.getInstance().getLogger().info("Added server " + serverType.name());
        if(isLobby)lobbyCount++;
        //updateListenerServers();
    }

    private DockerServiceResolver.DockerService getServerCluster(ServerRegistration.ServerType serverType) {
        return serverType == ServerRegistration.ServerType.LOBBY ? DockerServiceResolver.DockerService.LOBBY_SERVER_CLUSTER : DockerServiceResolver.DockerService.GAME_SERVER_CUSTER;
    }

    public void removeServer(int port, ServerRegistration.ServerType serverType) {
        boolean isLobby = serverType == ServerRegistration.ServerType.LOBBY;
        ServerManagerUtil.removeServer(serverType.name().toLowerCase() + "-" + Integer.toString(port));
        if(isLobby)lobbyCount--;
        //updateListenerServers();
    }
}
