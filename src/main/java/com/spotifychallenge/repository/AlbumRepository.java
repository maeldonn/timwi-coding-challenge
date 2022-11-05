package com.spotifychallenge.repository;

import com.spotifychallenge.dto.AlbumDto;

import java.util.Optional;

public interface AlbumRepository {

    Optional<AlbumDto> findAlbum(String albumId);

    AlbumDto addAlbumToPersonalList(AlbumDto album);

    void removeAlbumFromPersonalList(String albumId);

    AlbumDto addAlbumToFavorite(String albumId);

    AlbumDto removeAlbumFromFavorite(String albumId);
}
