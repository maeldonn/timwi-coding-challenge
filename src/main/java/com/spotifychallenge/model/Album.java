package com.spotifychallenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    private String id;

    private String title;

    private String artists;

    private String image;

    private LocalDate releaseDate;

    private Integer duration;

    private boolean personal;

    private boolean favorite;

    private List<Tag> tags;
}