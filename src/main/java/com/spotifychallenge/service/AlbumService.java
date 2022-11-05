package com.spotifychallenge.service;

import com.spotifychallenge.model.Album;

import java.util.List;

public interface AlbumService {

    List<Album> getAlbums(String searchFilter);

    Album addAlbumToPersonalList(String albumId);

    void removeAlbumFromPersonalList(String albumId);

    Album addAlbumToFavorites(String albumId);

    Album removeAlbumFromFavorites(String albumId);
}
