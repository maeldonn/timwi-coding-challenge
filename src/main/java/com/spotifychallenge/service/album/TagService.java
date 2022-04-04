package com.spotifychallenge.service.album;

import com.spotifychallenge.dto.model.album.TagDto;

/**
 * Service of Tag
 */
public interface TagService {

    /**
     * Create a new tag
     *
     * @param tagDto the tag to create
     * @return The tag created
     */
    TagDto createTag(TagDto tagDto);
}
