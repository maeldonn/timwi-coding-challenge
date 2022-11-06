package com.spotifychallenge.exception;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Value
@AllArgsConstructor
public class SpotifyChallengeError {

    String message;

    Throwable throwable;

    HttpStatus httpStatus;

    ZonedDateTime timestamp;
}
