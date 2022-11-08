package com.spotifychallenge.repository.mapper.impl;

import com.spotifychallenge.entity.TagEntity;
import com.spotifychallenge.model.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TagMapperTest {

    private final TagMapper mapper;

    public TagMapperTest() {
        mapper = new TagMapper();
    }

    private static Tag getModel() {
        return Tag.builder()
                .id(1L)
                .name("My tag")
                .albums(Collections.emptyList())
                .build();
    }

    @Test
    @DisplayName("it should return an entity")
    void mapToEntityTest() {
        // GIVEN
        Tag model = getModel();

        // WHEN
        TagEntity entity = mapper.mapToEntity(model);

        // THEN
        assertEquals(model.getId(), entity.getId());
        assertEquals(model.getName(), entity.getName());
    }

    private static TagEntity getEntity() {
        TagEntity entity = new TagEntity();
        entity.setId(1L);
        entity.setName("My tag");
        entity.setAlbums(Collections.emptyList());
        return entity;
    }

    @Test
    @DisplayName("it should return a model")
    void mapToModelTest() {
        // GIVEN
        TagEntity entity = getEntity();

        // WHEN
        Tag model = mapper.mapToModel(entity);

        // THEN
        assertEquals(entity.getId(), model.getId());
        assertEquals(entity.getName(), model.getName());
    }
}
