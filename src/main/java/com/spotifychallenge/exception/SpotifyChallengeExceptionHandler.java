package com.spotifychallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class SpotifyChallengeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class, SpotifyChallengeRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(SpotifyChallengeRequestException e) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        SpotifyChallengeException exception = new SpotifyChallengeException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exception, badRequest);
    }

    @ExceptionHandler(value = {LoginException.class})
    public ResponseEntity<Object> handleApiRequestException(LoginException e) {

        // TODO: Enable CORS and redirect with backend
        // return ResponseEntity
        //         .status(HttpStatus.FOUND)
        //         .location(URI.create(e.getRedirectUrl()))
        //         .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getRedirectUrl());
    }
}
