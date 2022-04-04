package com.spotifychallenge.dto.model.album;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * Tag Data Transfer Object
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagDto {

    private Long tagId = null;

    private String name = null;

    private Set<AlbumDto> albums = null;
}
