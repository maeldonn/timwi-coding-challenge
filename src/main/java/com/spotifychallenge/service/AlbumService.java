package com.spotifychallenge.service;

import com.spotifychallenge.dto.AlbumDto;

import java.io.IOException;
import java.util.List;

public interface AlbumService {

    List<AlbumDto> getAlbums(String searchFilter) throws IOException;

    AlbumDto addAlbumToPersonalList(String albumId);

    void removeAlbumFromPersonalList(String albumId);

    AlbumDto addAlbumToFavorites(String albumId);

    AlbumDto removeAlbumFromFavorites(String albumId);
}
