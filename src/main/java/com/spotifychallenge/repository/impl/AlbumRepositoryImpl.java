package com.spotifychallenge.repository.impl;

import com.spotifychallenge.entity.AlbumEntity;
import com.spotifychallenge.entity.TagEntity;
import com.spotifychallenge.model.Album;
import com.spotifychallenge.repository.AlbumRepository;
import com.spotifychallenge.repository.AlbumSpringDataRepository;
import com.spotifychallenge.repository.mapper.impl.AlbumMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepositoryImpl implements AlbumRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final AlbumSpringDataRepository repository;

    private final AlbumMapper mapper;

    public AlbumRepositoryImpl(AlbumSpringDataRepository repository, AlbumMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Album> getAlbums() {
        return mapper.mapToModelList(repository.findAll());
    }

    @Override
    public Optional<Album> findAlbum(String id) {
        Optional<AlbumEntity> album = repository.findById(id);
        return album.map(mapper::mapToModel);
    }

    @Override
    public Album addAlbumToPersonalList(Album album) {
        AlbumEntity albumToSave = mapper.mapToEntity(album);
        return mapper.mapToModel(repository.save(albumToSave));
    }

    @Override
    public void removeAlbumFromPersonalList(String id) {
        AlbumEntity albumToRemove = repository.getReferenceById(id);
        albumToRemove.getTags().stream()
                .filter(TagEntity::isMappedToOneAlbum)
                .forEach(entityManager::remove);
        repository.delete(albumToRemove);
    }

    @Override
    public Album toggleFavoriteAlbum(String id) {
        AlbumEntity album = repository.getReferenceById(id);
        album.setFavorite(!album.getFavorite());
        return mapper.mapToModel(album);
    }
}
