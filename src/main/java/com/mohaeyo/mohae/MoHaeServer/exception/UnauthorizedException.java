package com.mohaeyo.mohae.MoHaeServer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class UnauthorizedException extends HttpClientErrorException {
    public UnauthorizedException(HttpStatus statusCode) {
        super(statusCode);
    }
}
