package com.spotifychallenge.service.auth;

/**
 * Service of SpotifyAuth
 */
public interface SpotifyAuthService {

    /**
     * Login
     *
     * @param code TODO
     * @return the link to log in
     */
    void callback(String code);
}
