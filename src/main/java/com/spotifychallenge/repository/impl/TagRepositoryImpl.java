package com.spotifychallenge.repository.impl;

import com.spotifychallenge.entity.AlbumEntity;
import com.spotifychallenge.entity.TagEntity;
import com.spotifychallenge.model.Album;
import com.spotifychallenge.model.Tag;
import com.spotifychallenge.repository.TagRepository;
import com.spotifychallenge.repository.TagSpringDataRepository;
import com.spotifychallenge.repository.mapper.impl.TagMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TagRepositoryImpl implements TagRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final TagSpringDataRepository repository;

    private final TagMapper mapper;

    public TagRepositoryImpl(TagSpringDataRepository repository, TagMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Tag createTag(Tag tag) {
        TagEntity tagToSave = mapper.mapToEntity(tag);
        for (Album album : tag.getAlbums()) {
            entityManager.getReference(AlbumEntity.class, album.getId())
                    .getTags().add(tagToSave);
        }
        return mapper.mapToModel(repository.save(tagToSave));
    }
}