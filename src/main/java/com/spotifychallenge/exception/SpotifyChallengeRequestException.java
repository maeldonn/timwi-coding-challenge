package com.spotifychallenge.exception;

public class SpotifyChallengeRequestException extends RuntimeException {

    public SpotifyChallengeRequestException(String message) {
        super(message);
    }

    public SpotifyChallengeRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
