package com.spotifychallenge.exception.specific;

import com.spotifychallenge.exception.SpotifyChallengeException;

public class ApiSearchException extends SpotifyChallengeException {

    public ApiSearchException() {
        super("An error occured while using spotify API");
    }
}
