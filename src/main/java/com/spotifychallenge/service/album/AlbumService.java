package com.spotifychallenge.service.album;

import com.spotifychallenge.dto.model.album.AlbumDto;

import java.util.List;

/**
 * Service of Album
 */
public interface AlbumService {

    /**
     * Get all albums
     *
     * @param searchQuery The search query
     * @param personal Personal filter
     * @return a list of albums
     */
    List<AlbumDto> getAlbums(String searchQuery, Boolean personal);

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
     */
    void removeAlbumFromPersonalList(String albumId);

    /**
     * Update the album
     *
     * @param favorite The album to update
     * @return The updated album if albumRequest is valid
     */
    AlbumDto updateAlbumFavorite(String albumId, Boolean favorite);
}
