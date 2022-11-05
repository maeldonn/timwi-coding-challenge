package com.spotifychallenge.service.impl;

import com.spotifychallenge.model.Tag;
import com.spotifychallenge.repository.TagRepository;
import com.spotifychallenge.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.createTag(tag);
    }
}
