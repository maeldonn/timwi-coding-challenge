package com.spotifychallenge.service.impl;

import com.spotifychallenge.model.Tag;
import com.spotifychallenge.repository.TagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    @DisplayName("it should return the tag with an id")
    void createTagTest() {
        // GIVEN
        Tag tagToCreate = Tag.builder()
                .name("My tag")
                .albums(Collections.emptyList())
                .build();
        Tag createdTag = Tag.builder()
                .id(1L)
                .name("My tag")
                .albums(Collections.emptyList())
                .build();

        // MOCK
        when(tagRepository.createTag(any())).thenReturn(createdTag);

        // WHEN
        Tag tag = tagService.createTag(tagToCreate);

        // THEN
        assertNotNull(tag.getId());
    }
}
