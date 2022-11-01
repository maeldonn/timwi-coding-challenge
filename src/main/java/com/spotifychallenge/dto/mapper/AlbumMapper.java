package com.spotifychallenge.dto.mapper;


import com.spotifychallenge.controllers.v1.request.AlbumRequest;
import com.spotifychallenge.dto.model.AlbumDto;
import com.spotifychallenge.model.Album;

public class AlbumMapper {

    private AlbumMapper() {
        // NO INSTANCE
    }

    public static AlbumDto toAlbumDto(Album album) {
        AlbumDto albumDto = new AlbumDto();

        albumDto.setAlbumId(album.getAlbumId());
        albumDto.setTitle(album.getTitle());
        albumDto.setDate(album.getDate());
        albumDto.setDuration(album.getDuration());
        albumDto.setFavorite(album.getFavorite());

        return albumDto;
    }

    public static Album toAlbum(AlbumDto albumDto) {
        Album album = new Album();

        album.setAlbumId(albumDto.getAlbumId());
        album.setTitle(albumDto.getTitle());
        album.setDate(albumDto.getDate());
        album.setDuration(albumDto.getDuration());
        album.setFavorite(albumDto.getFavorite());

        return album;
    }

    public static AlbumDto toAlbumDto(AlbumRequest albumRequest) {
        AlbumDto albumDto = new AlbumDto();

        albumDto.setAlbumId(albumRequest.getAlbumId());
        albumDto.setTitle(albumRequest.getTitle());
        albumDto.setDuration(albumRequest.getDuration());
        albumDto.setFavorite(albumRequest.getFavorite());

        return albumDto;
    }
}
