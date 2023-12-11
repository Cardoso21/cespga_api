package br.com.cespga.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuhenticationException extends AuthenticationException {
    private static final Long serialVersionUID = 1L;

    public InvalidJwtAuhenticationException(String ex) {
        super(ex);
    }
}
