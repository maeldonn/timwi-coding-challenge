package com.spotifychallenge.dto.mapper.spotify;

import com.spotifychallenge.dto.model.album.AlbumDto;
import com.spotifychallenge.dto.model.spotify.SpotifyAlbumDto;

/**
 * Spotify Mapper
 */
public class SpotifyMapper {

    private SpotifyMapper() {
        // NO INSTANCE
    }

    /**
     * Map SpotifyAlbumDto to AlbumDto
     *
     * @param spotifyAlbumDto
     * @return albumDto
     */
    public static AlbumDto toAlbumDto(SpotifyAlbumDto spotifyAlbumDto) {
        return new AlbumDto()
                .setAlbumId(spotifyAlbumDto.getAlbumId())
                .setTitle(spotifyAlbumDto.getTitle())
                .setArtist(spotifyAlbumDto.getArtists().get(0).getName())
                .setImage(spotifyAlbumDto.getImages().get(0).getUrl())
                .setDate(spotifyAlbumDto.getDate())
                .setDuration(spotifyAlbumDto.getDuration());
    }
}
