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
        albums.forEach(album -> album.setPersonal(albumRepository.findAlbum(album.getId()).isPresent()));
        return albums;
    }

    @Override
    public List<Album> getPersonalAlbums() {
        return albumRepository.getAlbums();
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
    public Album toggleFavoriteAlbum(String albumId) {
        return albumRepository.toggleFavoriteAlbum(albumId);
    }
}
