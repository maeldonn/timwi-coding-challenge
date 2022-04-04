package com.spotifychallenge.dto.mapper.album;


import com.spotifychallenge.controllers.v1.request.AlbumRequest;
import com.spotifychallenge.dto.model.album.AlbumDto;
import com.spotifychallenge.model.Album;

import java.util.stream.Collectors;

/**
 * Album Mapper
 */
public class AlbumMapper {

    private AlbumMapper() {
        // NO INSTANCE
    }

    /**
     * Map Album to AlbumDto
     *
     * @param album
     * @return albumDto
     */
    public static AlbumDto toAlbumDto(Album album) {
        return new AlbumDto()
                .setAlbumId(album.getAlbumId())
                .setTitle(album.getTitle())
                .setArtist(album.getArtist())
                .setImage(album.getImage())
                .setDate(album.getDate())
                .setDuration(album.getDuration())
                .setFavorite(album.getFavorite());
    }

    /**
     * Map Album to AlbumDto with tags
     *
     * @param album
     * @return albumDto
     */
    public static AlbumDto toAlbumDtoWithTags(Album album) {
        return new AlbumDto()
                .setAlbumId(album.getAlbumId())
                .setTitle(album.getTitle())
                .setArtist(album.getArtist())
                .setImage(album.getImage())
                .setDate(album.getDate())
                .setDuration(album.getDuration())
                .setFavorite(album.getFavorite())
                .setTags(album.getTags().stream().map(TagMapper::toTagDto).collect(Collectors.toSet()));
    }

    /**
     * Map AlbumDto to Album
     *
     * @param albumDto
     * @return album
     */
    public static Album toAlbum(AlbumDto albumDto) {
        return new Album()
                .setAlbumId(albumDto.getAlbumId())
                .setTitle(albumDto.getTitle())
                .setArtist(albumDto.getArtist())
                .setImage(albumDto.getImage())
                .setDate(albumDto.getDate())
                .setDuration(albumDto.getDuration())
                .setFavorite(albumDto.getFavorite());
    }

    /**
     * Map AlbumRequest to AlbumDto
     *
     * @param albumRequest
     * @return albumDto
     */
    public static AlbumDto toAlbumDto(AlbumRequest albumRequest) {
        return new AlbumDto()
                .setAlbumId(albumRequest.getAlbumId())
                .setTitle(albumRequest.getTitle())
                .setArtist(albumRequest.getArtist())
                .setImage(albumRequest.getImage())
                .setDate(albumRequest.getDate())
                .setDuration(albumRequest.getDuration())
                .setPersonal(albumRequest.getPersonal())
                .setFavorite(albumRequest.getFavorite());
    }
}
