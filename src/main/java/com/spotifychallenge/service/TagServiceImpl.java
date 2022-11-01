package com.spotifychallenge.service;

import com.spotifychallenge.dto.mapper.TagMapper;
import com.spotifychallenge.dto.model.TagDto;
import com.spotifychallenge.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public TagDto createTag(TagDto tagDto) {

        // Creation of the tag
        tagRepository.saveAndFlush(TagMapper.toTag(tagDto));

        // Get the new tag and return it
        return TagMapper.toTagDto(tagRepository.findByName(tagDto.getName()));
    }
}
