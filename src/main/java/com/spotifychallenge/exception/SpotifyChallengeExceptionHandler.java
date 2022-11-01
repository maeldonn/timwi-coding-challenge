package com.spotifychallenge.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

@ControllerAdvice
public class SpotifyChallengeExceptionHandler {

    @ExceptionHandler({ SpotifyChallengeGenericException.class })
    public ResponseEntity<SpotifyChallengeException> handleApiRequestException(Exception e) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        SpotifyChallengeException exception = new SpotifyChallengeException(
            e.getMessage(),
            e,
            badRequest,
            ZonedDateTime.now()
        );

        return new ResponseEntity<>(exception, badRequest);
    }
}
