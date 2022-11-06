package com.spotifychallenge.service;

import com.spotifychallenge.model.Album;

import java.util.List;

public interface AlbumService {

    List<Album> getAlbums(String searchFilter);

    List<Album> getPersonalAlbums();

    Album addAlbumToPersonalList(String id);

    void removeAlbumFromPersonalList(String id);

    Album toggleFavoriteAlbum(String id);
}
