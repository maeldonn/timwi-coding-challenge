package com.spotifychallenge.service;

import com.spotifychallenge.dto.mapper.AlbumMapper;
import com.spotifychallenge.dto.model.AlbumDto;
import com.spotifychallenge.model.Album;
import com.spotifychallenge.repository.AlbumRepository;
import com.spotifychallenge.util.SpotifyRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final SpotifyRestClient spotifyRestClient;

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(SpotifyRestClient spotifyRestClient, AlbumRepository albumRepository) {
        this.spotifyRestClient = spotifyRestClient;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<AlbumDto> getAlbums(String searchFilter) throws IOException {
        List<AlbumDto> albums = spotifyRestClient.searchAlbums(searchFilter);

        // Check for favorites in personal list
        for (AlbumDto albumDto : albums) {

            // Search the album in personal list
            Optional<Album> album = albumRepository.findById(albumDto.getAlbumId());

            // Set favorite
            album.ifPresentOrElse(value -> albumDto.setFavorite(value.getFavorite()), () -> albumDto.setFavorite(false));
        }

        return albums;
    }

    @Override
    public AlbumDto addAlbumToPersonalList(String albumId) {
        try {
            // Search if id is valid
            AlbumDto albumDto = spotifyRestClient.searchAlbum(albumId);

            // If it is a valid id, add album to personal list
            Album album = AlbumMapper.toAlbum(albumDto);
            albumRepository.saveAndFlush(album);

            return albumDto;

        } catch (IOException exception) {
            return null;
        }
    }

    @Override
    public void removeAlbumFromPersonalList(String albumId) {

        // Search the entity to delete
        Optional<Album> album = albumRepository.findById(albumId);

        // Then delete it if entity exist
        album.ifPresent(albumRepository::delete);
    }

    @Override
    public AlbumDto addAlbumToFavorites(AlbumDto albumDto) {
        try {
            // Search if id is valid
            spotifyRestClient.searchAlbum(albumDto.getAlbumId());

            // Add album to personal list if it is not already
            albumRepository.saveAndFlush(AlbumMapper.toAlbum(albumDto));

            // Add album to favorites
            albumRepository.setAlbumFavoriteById(true, albumDto.getAlbumId());

            // Return the entity updated
            return AlbumMapper.toAlbumDto(albumRepository.getById(albumDto.getAlbumId()));

        } catch (IOException exception) {
            return null;
        }
    }

    @Override
    public AlbumDto removeAlbumFromFavorites(AlbumDto albumDto) {

        // Check if album is in personal list
        if (albumRepository.findById(albumDto.getAlbumId()).isPresent()) {

            // Add album to favorites
            albumRepository.setAlbumFavoriteById(false, albumDto.getAlbumId());

            // Return the entity updated
            return AlbumMapper.toAlbumDto(albumRepository.getById(albumDto.getAlbumId()));
        }

        return null;
    }
}
