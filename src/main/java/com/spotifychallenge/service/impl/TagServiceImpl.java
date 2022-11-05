package com.spotifychallenge.service.impl;

import com.spotifychallenge.dto.TagDto;
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
    public TagDto createTag(TagDto tagDto) {
        return tagRepository.createTag(tagDto);
    }
}
