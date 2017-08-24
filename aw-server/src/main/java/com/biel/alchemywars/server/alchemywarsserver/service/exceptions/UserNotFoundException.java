package com.biel.alchemywars.server.alchemywarsserver.service.exceptions;


public class UserNotFoundException extends EntityNotFoundException{
    public UserNotFoundException(String userId) {
        super(1, "User", userId);
    }
}
