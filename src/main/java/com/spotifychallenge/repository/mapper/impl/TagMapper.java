package com.spotifychallenge.repository.mapper.impl;


import com.spotifychallenge.dto.TagDto;
import com.spotifychallenge.model.Tag;
import com.spotifychallenge.repository.mapper.SpotifyChallengeMapper;
import org.springframework.stereotype.Component;

@Component
public class TagMapper implements SpotifyChallengeMapper<TagDto, Tag> {

    @Override
    public Tag mapToEntity(TagDto model) {
        Tag tag = new Tag();
        tag.setTagId(model.getTagId());
        tag.setName(model.getName());
        return tag;
    }

    @Override
    public TagDto mapToModel(Tag entity) {
        return TagDto.builder()
            .tagId(entity.getTagId())
            .name(entity.getName())
            .build();
    }
}
