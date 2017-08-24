package com.biel.alchemywars.server.alchemywarsserver.service.exceptions;


public class ServerAlreadyExistsException extends EntityAlreadyExistsException{
    public ServerAlreadyExistsException(String serverId) {
        super(1, "Server", serverId);
    }
}
