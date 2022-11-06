package com.spotifychallenge.repository;

import com.spotifychallenge.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository {

    List<Album> getAlbums();

    Optional<Album> findAlbum(String albumId);

    Album addAlbumToPersonalList(Album album);

    void removeAlbumFromPersonalList(String albumId);

    Album toggleFavoriteAlbum(String albumId);
}
