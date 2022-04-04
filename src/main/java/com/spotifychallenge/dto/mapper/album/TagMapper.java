package com.spotifychallenge.dto.mapper.album;


import com.spotifychallenge.controllers.v1.request.TagRequest;
import com.spotifychallenge.dto.model.album.TagDto;
import com.spotifychallenge.model.Tag;

import java.util.stream.Collectors;

/**
 * Tag Mapper
 */
public class TagMapper {

    private TagMapper() {
        // NO INSTANCE
    }

    /**
     * Map Tag to TagDto
     *
     * @param tag
     * @return tagDto
     */
    public static TagDto toTagDto(Tag tag) {
        return new TagDto()
                .setTagId(tag.getTagId())
                .setName(tag.getName());
    }

    /**
     * Map tagDto to Tag
     *
     * @param tagDto
     * @return tag
     */
    public static Tag toTag(TagDto tagDto) {
        return new Tag()
                .setTagId(tagDto.getTagId())
                .setName(tagDto.getName())
                .setAlbums(tagDto.getAlbums().stream().map(AlbumMapper::toAlbum).collect(Collectors.toSet()));
    }

    /**
     * Map TagRequest to TagDto
     *
     * @param tagRequest
     * @return tagDto
     */
    public static TagDto toTagDto(TagRequest tagRequest) {
        return new TagDto()
                .setName(tagRequest.getName())
                .setAlbums(tagRequest.getAlbums().stream().map(AlbumMapper::toAlbumDto).collect(Collectors.toSet()));
    }
}
