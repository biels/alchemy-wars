package com.biel.alchemywars.server.alchemywarsserver.service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class APIException extends RuntimeException {
    private final int code;
    private final String message;
}
