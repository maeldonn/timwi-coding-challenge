package com.spotifychallenge.repository.impl;

import com.spotifychallenge.dto.TagDto;
import com.spotifychallenge.model.Tag;
import com.spotifychallenge.repository.TagRepository;
import com.spotifychallenge.repository.TagSpringDataRepository;
import com.spotifychallenge.repository.mapper.impl.TagMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepositoryImpl implements TagRepository {

    private final TagSpringDataRepository repository;

    private final TagMapper mapper;

    public TagRepositoryImpl(TagSpringDataRepository repository, TagMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TagDto createTag(TagDto tag) {
        Tag tagToSave = mapper.mapToEntity(tag);
        return mapper.mapToModel(repository.save(tagToSave));
    }
}
