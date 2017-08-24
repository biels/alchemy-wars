package com.biel.alchemywars.server.alchemywarsserver.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public abstract class EntityNotFoundException extends APIException {
    public EntityNotFoundException(int code, String entityName, String entityIdentifier) {
        super(code, MessageFormat.format("{0} {1} not found", entityName, entityIdentifier));
    }
}
