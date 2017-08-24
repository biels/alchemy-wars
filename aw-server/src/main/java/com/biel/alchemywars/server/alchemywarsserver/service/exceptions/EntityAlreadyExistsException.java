package com.biel.alchemywars.server.alchemywarsserver.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(code = HttpStatus.CONFLICT)
public abstract class EntityAlreadyExistsException extends APIException {
    public EntityAlreadyExistsException(int code, String entityName, String entityIdentifier) {
        super(code, MessageFormat.format("{0} {1} already exists", entityName, entityIdentifier));
    }
}
