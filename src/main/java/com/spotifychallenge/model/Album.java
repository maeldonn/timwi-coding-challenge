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

    private String albumId;

    private String title;

    private LocalDate date;

    private Integer duration;

    private boolean favorite;

    private List<Tag> tags;
}