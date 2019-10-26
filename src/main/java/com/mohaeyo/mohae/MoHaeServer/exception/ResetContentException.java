package com.mohaeyo.mohae.MoHaeServer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.RESET_CONTENT)
public class ResetContentException extends RuntimeException {
    public ResetContentException(String message) {
        super(message);
    }
}
