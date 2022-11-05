package com.spotifychallenge.repository.impl;

import com.spotifychallenge.dto.AlbumDto;
import com.spotifychallenge.model.Album;
import com.spotifychallenge.repository.AlbumRepository;
import com.spotifychallenge.repository.AlbumSpringDataRepository;
import com.spotifychallenge.repository.mapper.impl.AlbumMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AlbumRepositoryImpl implements AlbumRepository {

    private final AlbumSpringDataRepository repository;

    private final AlbumMapper mapper;

    public AlbumRepositoryImpl(AlbumSpringDataRepository repository, AlbumMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<AlbumDto> findAlbum(String albumId) {
        Optional<Album> album = repository.findById(albumId);
        return album.map(mapper::mapToModel);
    }

    @Override
    public AlbumDto addAlbumToPersonalList(AlbumDto album) {
        Album albumToSave = mapper.mapToEntity(album);
        return mapper.mapToModel(repository.save(albumToSave));
    }

    @Override
    public void removeAlbumFromPersonalList(String albumId) {
        repository.findById(albumId).ifPresent(repository::delete);
    }

    @Override
    public AlbumDto addAlbumToFavorite(String albumId) {
        Album album = repository.getReferenceById(albumId);
        album.setFavorite(true);
        return mapper.mapToModel(album);
    }

    @Override
    public AlbumDto removeAlbumFromFavorite(String albumId) {
        Album album = repository.getReferenceById(albumId);
        album.setFavorite(false);
        return mapper.mapToModel(album);
    }
}
