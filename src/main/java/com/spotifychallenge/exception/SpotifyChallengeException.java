package com.spotifychallenge.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class SpotifyChallengeException {

    String message;

    Throwable throwable;

    HttpStatus httpStatus;

    ZonedDateTime timestamp;
}
