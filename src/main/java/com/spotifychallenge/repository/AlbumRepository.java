package com.spotifychallenge.repository;

import com.spotifychallenge.model.Album;

import java.util.Optional;

public interface AlbumRepository {

    Optional<Album> findAlbum(String albumId);

    Album addAlbumToPersonalList(Album album);

    void removeAlbumFromPersonalList(String albumId);

    Album toggleFavoriteAlbum(String albumId);
}
