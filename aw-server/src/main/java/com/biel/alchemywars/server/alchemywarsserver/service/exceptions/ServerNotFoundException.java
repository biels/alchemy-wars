package com.biel.alchemywars.server.alchemywarsserver.service.exceptions;


public class ServerNotFoundException extends EntityNotFoundException{
    public ServerNotFoundException(String serverId) {
        super(1, "Server", serverId);
    }
}
