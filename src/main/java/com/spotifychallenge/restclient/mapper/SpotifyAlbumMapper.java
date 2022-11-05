package com.spotifychallenge.restclient.mapper;

import com.spotifychallenge.dto.AlbumDto;
import com.spotifychallenge.restclient.dto.SpotifyAlbum;

public class SpotifyAlbumMapper {

    private SpotifyAlbumMapper() {
        // NO INSTANCE
    }

    public static AlbumDto mapToModel(SpotifyAlbum spotifyAlbum) {
        return AlbumDto.builder()
                .albumId(spotifyAlbum.getAlbumId())
                .title(spotifyAlbum.getTitle())
                .date(spotifyAlbum.getDate())
                .duration(spotifyAlbum.getDuration())
                .build();
    }
}
