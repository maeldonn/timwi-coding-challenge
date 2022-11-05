package com.spotifychallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class SpotifyChallengeExceptionHandler {

    @ExceptionHandler({SpotifyChallengeException.class})
    public ResponseEntity<SpotifyChallengeError> handleApiRequestException(Exception e) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        SpotifyChallengeError exception = new SpotifyChallengeError(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exception, badRequest);
    }
}
