package com.biel.alchemywars.server.alchemywarsserver.graphql;

import com.biel.alchemywars.server.alchemywarsserver.model.server.LobbyServer;
import com.biel.alchemywars.server.alchemywarsserver.repositories.LobbyServerRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver{
    @Autowired
    LobbyServerRepository lobbyServerRepository;
    public Iterable<LobbyServer> allLobbyServers(){
        return lobbyServerRepository.findAll();
    }
    public LobbyServer lobbyServer(Long id){
        return lobbyServerRepository.findOne(id);
    }
}
