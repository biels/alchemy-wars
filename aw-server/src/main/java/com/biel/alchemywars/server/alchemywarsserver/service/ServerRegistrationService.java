package com.biel.alchemywars.server.alchemywarsserver.service;

import com.biel.alchemywars.communication.rest.ServerRegistrationRequest;
import com.biel.alchemywars.server.alchemywarsserver.components.BungeeController;
import com.biel.alchemywars.server.alchemywarsserver.model.server.LobbyServer;
import com.biel.alchemywars.server.alchemywarsserver.model.server.MatchServer;
import com.biel.alchemywars.server.alchemywarsserver.model.server.Server;
import com.biel.alchemywars.server.alchemywarsserver.repositories.LobbyServerRepository;
import com.biel.alchemywars.server.alchemywarsserver.repositories.MatchServerRepository;
import com.biel.alchemywars.server.alchemywarsserver.repositories.ServerRepository;
import com.biel.alchemywars.server.alchemywarsserver.service.exceptions.ServerAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/servers/register")
public class ServerRegistrationService {
    @Autowired
    ServerRepository serverRepository;
    @Autowired
    LobbyServerRepository lobbyServerRepository;
    @Autowired
    MatchServerRepository matchServerRepository;
    @Autowired
    RepositoryEntityLinks repositoryEntityLinks;
    @Autowired
    BungeeController bungeeController;
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> registerServer(@RequestBody ServerRegistrationRequest serverRegistrationRequest){
        Optional<Server> serverOptional = serverRepository.findByPort(serverRegistrationRequest.getPort());
        if(serverOptional.isPresent()) throw new ServerAlreadyExistsException(serverOptional.get().toString());
        URI location = null;
        Server server = null;
        switch (serverRegistrationRequest.getServerType()){
            case LOBBY:
                LobbyServer lobbyServer = new LobbyServer();
                lobbyServer.setPort(serverRegistrationRequest.getPort());
                lobbyServer.setStatus(Server.Status.UP);
                lobbyServer = lobbyServerRepository.save(lobbyServer);
                server = lobbyServer;
                location = URI.create(repositoryEntityLinks.linkToSingleResource(LobbyServer.class, lobbyServer.getId()).getHref());
                break;
            case GAME:
                MatchServer matchServer = matchServerRepository.save(new MatchServer());
                server = matchServer;
                location = URI.create(repositoryEntityLinks.linkToSingleResource(MatchServer.class, matchServer.getId()).getHref());
                break;
        }
        bungeeController.registerServer(server);
        return ResponseEntity.created(location).build();
    }
}
