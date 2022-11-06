package com.spotifychallenge.exception.specific;

import com.spotifychallenge.exception.SpotifyChallengeException;

public class AlbumMappingException extends SpotifyChallengeException {

    public AlbumMappingException() {
        super("An error occured while converting api data.");
    }
}
