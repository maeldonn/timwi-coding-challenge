package com.spotifychallenge.repository;

import com.spotifychallenge.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository {

    List<Album> getAlbums();

    Optional<Album> findAlbum(String id);

    Album addAlbumToPersonalList(Album album);

    void removeAlbumFromPersonalList(String id);

    Album toggleFavoriteAlbum(String id);
}
