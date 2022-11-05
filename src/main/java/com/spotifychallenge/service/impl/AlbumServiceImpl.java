package com.spotifychallenge.service.impl;

import com.spotifychallenge.dto.AlbumDto;
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
    public List<AlbumDto> getAlbums(String searchFilter) {
        List<AlbumDto> albums = spotifyRestClient.searchAlbums(searchFilter);

        for (AlbumDto album : albums) {
            albumRepository.findAlbum(album.getAlbumId()).ifPresent(personalAlbum -> album.setFavorite(personalAlbum.isFavorite()));
        }

        return albums;
    }

    @Override
    @Transactional
    public AlbumDto addAlbumToPersonalList(String albumId) {
        AlbumDto albumDto = spotifyRestClient.searchAlbum(albumId);
        return albumRepository.addAlbumToPersonalList(albumDto);
    }

    @Override
    @Transactional
    public void removeAlbumFromPersonalList(String albumId) {
        albumRepository.removeAlbumFromPersonalList(albumId);
    }

    @Override
    @Transactional
    public AlbumDto addAlbumToFavorites(String albumId) {
        return albumRepository.addAlbumToFavorite(albumId);
    }

    @Override
    @Transactional
    public AlbumDto removeAlbumFromFavorites(String albumId) {
        return albumRepository.removeAlbumFromFavorite(albumId);
    }
}
