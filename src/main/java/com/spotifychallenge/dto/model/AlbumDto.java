package com.spotifychallenge.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumDto {

    @JsonProperty("id")
    private String albumId;

    @JsonProperty("name")
    private String title;

    @JsonProperty("release_date")
    private Date date;

    @JsonProperty("total_tracks")
    private Integer duration;

    private Boolean favorite;

    private List<TagDto> tags;
}
