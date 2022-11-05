package com.spotifychallenge.restclient.mapper;

import com.spotifychallenge.model.Album;
import com.spotifychallenge.restclient.dto.SpotifyAlbum;

public class SpotifyAlbumMapper {

    private SpotifyAlbumMapper() {
        // NO INSTANCE
    }

    public static Album mapToModel(SpotifyAlbum spotifyAlbum) {
        return Album.builder()
                .id(spotifyAlbum.getId())
                .title(spotifyAlbum.getTitle())
                .releaseDate(spotifyAlbum.getReleaseDate())
                .duration(spotifyAlbum.getDuration())
                .build();
    }
}
