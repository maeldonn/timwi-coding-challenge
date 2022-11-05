package com.spotifychallenge.repository;

import com.spotifychallenge.dto.TagDto;

public interface TagRepository {

    TagDto createTag(TagDto tag);
}
