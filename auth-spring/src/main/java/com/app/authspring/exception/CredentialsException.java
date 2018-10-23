package com.app.authspring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CredentialsException extends RuntimeException{

    public CredentialsException(String msg, Throwable cause){
        super(msg, cause);
    }

    public CredentialsException(String msg){
        super(msg);
    }

    public CredentialsException(Throwable cause){
        super(cause);
    }
}
