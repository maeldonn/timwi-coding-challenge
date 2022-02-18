package com.spotifychallenge.service;

import com.spotifychallenge.dto.model.AlbumDto;

import java.io.IOException;
import java.util.List;

/**
 * Service of Album
 */
public interface AlbumService {

    /**
     * Search for spotify albums
     *
     * @param searchFilter The search filter
     * @return 20 albums
     * @throws IOException
     */
    List<AlbumDto> getAlbums(String searchFilter) throws IOException;

    /**
     * Add an album to personal list
     *
     * @param albumId The album id
     * @return The album if the id is valid
     */
    AlbumDto addAlbumToPersonalList(String albumId);

    /**
     * Remove an album from personal list
     *
     * @param albumId The album id
     * @return OK
     */
    void removeAlbumFromPersonalList(String albumId);

    /**
     * Add an album to favorites
     *
     * @param albumDto The album to add
     * @return The updated album if albumRequest is valid
     */
    AlbumDto addAlbumToFavorites(AlbumDto albumDto);

    /**
     * Remove an album from favorites
     *
     * @param albumDto The album to remove
     * @return The updated album if albumRequest is valid
     */
    AlbumDto removeAlbumFromFavorites(AlbumDto albumDto);
}
