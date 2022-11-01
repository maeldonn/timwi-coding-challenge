package com.spotifychallenge.service;

import com.spotifychallenge.dto.model.AlbumDto;

import java.io.IOException;
import java.util.List;

public interface AlbumService {

    List<AlbumDto> getAlbums(String searchFilter) throws IOException;

    AlbumDto addAlbumToPersonalList(String albumId);

    void removeAlbumFromPersonalList(String albumId);

    AlbumDto addAlbumToFavorites(AlbumDto albumDto);

    AlbumDto removeAlbumFromFavorites(AlbumDto albumDto);
}
