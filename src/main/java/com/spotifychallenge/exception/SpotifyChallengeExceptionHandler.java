package com.spotifychallenge.exception;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static com.spotifychallenge.constants.SpotifyConstants.DEFAULT_ERROR_MESSAGE;

@ControllerAdvice
public class SpotifyChallengeExceptionHandler {

    private final Environment environment;

    public SpotifyChallengeExceptionHandler(Environment environment) {
        this.environment = environment;
    }

    @ExceptionHandler({SpotifyChallengeException.class})
    public ResponseEntity<SpotifyChallengeError> handleApiRequestException(Exception e) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        SpotifyChallengeError exception = new SpotifyChallengeError(
                getErrorMessage(e.getMessage()),
                e,
                badRequest,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exception, badRequest);
    }

    private String getErrorMessage(String specificMessage) {
        if (Arrays.stream(environment.getActiveProfiles()).toList().contains("dev")) {
            return specificMessage;
        }
        return DEFAULT_ERROR_MESSAGE;
    }
}
