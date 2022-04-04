package com.spotifychallenge.exception;

import lombok.Getter;

@Getter
public class LoginException extends RuntimeException {

    private final String redirectUrl;

    public LoginException(String message, String redirectUrl) {
        super(message);
        this.redirectUrl = redirectUrl;
    }
}
