package com.spotifychallenge.dto.model.album;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

/**
 * Album Data Transfer Object
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class AlbumDto {

    private String albumId = null;

    private String title = null;

    private String artist = null;

    private String image = null;

    private Date date = null;

    private Integer duration = null;

    private Boolean personal = false;

    private Boolean favorite = false;

    private Set<TagDto> tags = null;
}
