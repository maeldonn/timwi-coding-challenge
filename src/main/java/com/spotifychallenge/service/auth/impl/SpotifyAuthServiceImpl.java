package com.spotifychallenge.service.auth.impl;

import com.spotifychallenge.service.auth.SpotifyAuthService;
import com.spotifychallenge.util.SpotifyRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link SpotifyAuthService}
 */
@Service
public class SpotifyAuthServiceImpl implements SpotifyAuthService{

    //SERVICES
    private final SpotifyRestClient spotifyRestClient;

    /**
     * {@inheritDoc}
     */
    @Autowired
    public SpotifyAuthServiceImpl(SpotifyRestClient spotifyRestClient) {
        this.spotifyRestClient = spotifyRestClient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void callback(String code) {
        spotifyRestClient.callback(code);
    }
}
