package com.spotifychallenge.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TagDto {

    private Long tagId;

    private String name;

    private List<AlbumDto> albums;
}
