package com.spotifychallenge.exception.specific;

import com.spotifychallenge.exception.SpotifyChallengeException;

public class AlbumNotFoundException extends SpotifyChallengeException {

    public AlbumNotFoundException(String message) {
        super(message);
    }
}
