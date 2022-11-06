package com.spotifychallenge.controllers.v1.request;

import lombok.Value;

import java.time.LocalDate;

@Value
public class AlbumRequest {

    String id;

    String title;

    String artists;

    String image;

    LocalDate releaseDate;

    Integer duration;

    boolean personal;

    boolean favorite;
}
