package com.spotifychallenge.repository.mapper.impl;

import com.spotifychallenge.model.Tag;
import com.spotifychallenge.entity.TagEntity;
import com.spotifychallenge.repository.mapper.SpotifyChallengeMapper;
import org.springframework.stereotype.Component;

@Component
public class TagMapper implements SpotifyChallengeMapper<Tag, TagEntity> {

    @Override
    public TagEntity mapToEntity(Tag model) {
        TagEntity tag = new TagEntity();
        tag.setId(model.getId());
        tag.setName(model.getName());
        return tag;
    }

    @Override
    public Tag mapToModel(TagEntity entity) {
        return Tag.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
