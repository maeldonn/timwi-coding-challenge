package com.spotifychallenge.repository.impl;

import com.spotifychallenge.model.Tag;
import com.spotifychallenge.repository.TagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class TagRepositoryImplTest {

    private final TagRepository tagRepository;

    @Autowired
    public TagRepositoryImplTest(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Test
    @DisplayName("it should create a tag with an id")
    void createTagTest() {
        // GIVEN
        Tag tagToSave = Tag.builder()
                .name("My new tag")
                .albums(Collections.emptyList())
                .build();

        // WHEN
        final Tag tag = tagRepository.createTag(tagToSave);

        // THAT
        assertNotNull(tag.getId());
    }
}
