package com.spotifychallenge.repository.impl;

import com.spotifychallenge.model.Album;
import com.spotifychallenge.entity.AlbumEntity;
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
    public Optional<Album> findAlbum(String albumId) {
        Optional<AlbumEntity> album = repository.findById(albumId);
        return album.map(mapper::mapToModel);
    }

    @Override
    public Album addAlbumToPersonalList(Album album) {
        AlbumEntity albumToSave = mapper.mapToEntity(album);
        return mapper.mapToModel(repository.save(albumToSave));
    }

    @Override
    public void removeAlbumFromPersonalList(String albumId) {
        repository.findById(albumId).ifPresent(repository::delete);
    }

    @Override
    public Album addAlbumToFavorite(String albumId) {
        AlbumEntity album = repository.getReferenceById(albumId);
        album.setFavorite(true);
        return mapper.mapToModel(album);
    }

    @Override
    public Album removeAlbumFromFavorite(String albumId) {
        AlbumEntity album = repository.getReferenceById(albumId);
        album.setFavorite(false);
        return mapper.mapToModel(album);
    }
}
