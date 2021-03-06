package com.spotifychallenge.constants;

/**
 * URL of Spotify API
 */
public class SpotifyConstants {

    private SpotifyConstants() {
        // NO INSTANCE
    }

    public static final String GET_ALBUMS = "https://api.spotify.com/v1/search?type=album&q=";

    public static final String GET_ALBUM = "https://api.spotify.com/v1/albums/";

    public static final String BASE_URL = "https://api.spotify.com/v1/";
}
