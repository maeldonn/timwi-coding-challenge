package com.spotifychallenge.service.impl;

import com.spotifychallenge.model.Album;
import com.spotifychallenge.repository.AlbumRepository;
import com.spotifychallenge.restclient.SpotifyRestClient;
import com.spotifychallenge.service.AlbumService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final SpotifyRestClient spotifyRestClient;

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(SpotifyRestClient spotifyRestClient, AlbumRepository albumRepository) {
        this.spotifyRestClient = spotifyRestClient;
        this.albumRepository = albumRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Album> getAlbums(String searchFilter) {
        List<Album> albums = spotifyRestClient.searchAlbums(searchFilter);

        for (Album album : albums) {
            albumRepository.findAlbum(album.getId()).ifPresent(personalAlbum -> album.setFavorite(personalAlbum.isFavorite()));
        }

        return albums;
    }

    @Override
    @Transactional
    public Album addAlbumToPersonalList(String albumId) {
        Album album = spotifyRestClient.searchAlbum(albumId);
        return albumRepository.addAlbumToPersonalList(album);
    }

    @Override
    @Transactional
    public void removeAlbumFromPersonalList(String albumId) {
        albumRepository.removeAlbumFromPersonalList(albumId);
    }

    @Override
    @Transactional
    public Album addAlbumToFavorites(String albumId) {
        return albumRepository.addAlbumToFavorite(albumId);
    }

    @Override
    @Transactional
    public Album removeAlbumFromFavorites(String albumId) {
        return albumRepository.removeAlbumFromFavorite(albumId);
    }
}
