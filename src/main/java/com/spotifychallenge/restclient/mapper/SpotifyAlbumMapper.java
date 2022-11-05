package com.spotifychallenge.restclient.mapper;

import com.spotifychallenge.model.Album;
import com.spotifychallenge.restclient.dto.SpotifyAlbum;

public class SpotifyAlbumMapper {

    private SpotifyAlbumMapper() {
        // NO INSTANCE
    }

    public static Album mapToModel(SpotifyAlbum spotifyAlbum) {
        return Album.builder()
                .albumId(spotifyAlbum.getAlbumId())
                .title(spotifyAlbum.getTitle())
                .date(spotifyAlbum.getDate())
                .duration(spotifyAlbum.getDuration())
                .build();
    }
}
