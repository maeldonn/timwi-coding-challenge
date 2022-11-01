package com.spotifychallenge.dto.mapper;


import com.spotifychallenge.controllers.v1.request.TagRequest;
import com.spotifychallenge.dto.model.AlbumDto;
import com.spotifychallenge.dto.model.TagDto;
import com.spotifychallenge.model.Album;
import com.spotifychallenge.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagMapper {

    private TagMapper() {
        // NO INSTANCE
    }

    public static TagDto toTagDto(Tag tag) {
        TagDto tagDto = new TagDto();

        tagDto.setTagId(tag.getTagId());
        tagDto.setName(tag.getName());

        // Albums mapping
        List<Album> albums = tag.getAlbums();
        List<AlbumDto> albumDtos = new ArrayList<>();
        for (Album album : albums) {
            albumDtos.add(AlbumMapper.toAlbumDto(album));
        }
        tagDto.setAlbums(albumDtos);

        return tagDto;
    }

    public static Tag toTag(TagDto tagDto) {
        Tag tag = new Tag();

        tag.setTagId(tagDto.getTagId());
        tag.setName(tagDto.getName());

        // Albums mapping
        List<AlbumDto> albumDtos = tagDto.getAlbums();
        List<Album> albums = new ArrayList<>();
        for (AlbumDto albumDto : albumDtos) {
            albums.add(AlbumMapper.toAlbum(albumDto));
        }
        tag.setAlbums(albums);

        return tag;
    }

    public static TagDto toTagDto(TagRequest tagRequest) {
        TagDto tagDto = new TagDto();

        tagDto.setName(tagRequest.getName());
        tagDto.setAlbums(tagRequest.getAlbums());

        return tagDto;
    }
}
