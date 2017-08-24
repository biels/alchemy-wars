package com.biel.alchemywars.communication.rest;

import lombok.Data;
import lombok.NonNull;

@Data
public class ServerRegistrationRequest {
    public enum ServerType{LOBBY, GAME}
    @NonNull private ServerType serverType;
    @NonNull private Integer port;
}
