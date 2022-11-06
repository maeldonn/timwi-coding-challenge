package com.spotifychallenge.service;

import com.spotifychallenge.model.Album;

import java.util.List;

public interface AlbumService {

    List<Album> getAlbums(String searchFilter);

    List<Album> getPersonalAlbums();

    Album addAlbumToPersonalList(String albumId);

    void removeAlbumFromPersonalList(String albumId);

    Album toggleFavoriteAlbum(String albumId);
}
