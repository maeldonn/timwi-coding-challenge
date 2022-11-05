package com.spotifychallenge.restclient.mapper;

import com.spotifychallenge.exception.SpotifyChallengeException;
import com.spotifychallenge.model.Album;
import com.spotifychallenge.restclient.dto.SpotifyAlbum;

import java.time.LocalDate;

public class SpotifyAlbumMapper {

    private SpotifyAlbumMapper() {
        // NO INSTANCE
    }

    public static Album mapToModel(SpotifyAlbum spotifyAlbum) {
        return Album.builder()
                .id(spotifyAlbum.getId())
                .title(spotifyAlbum.getTitle())
                .releaseDate(getReleaseDate(spotifyAlbum))
                .duration(spotifyAlbum.getDuration())
                .build();
    }

    private static LocalDate getReleaseDate(SpotifyAlbum spotifyAlbum) {
        String releaseDatePrecision = spotifyAlbum.getReleaseDatePrecision();
        return switch (releaseDatePrecision) {
            case "day" -> LocalDate.parse(spotifyAlbum.getReleaseDate());
            case "month" -> getReleaseDateByMonth(spotifyAlbum.getReleaseDate());
            case "year" -> getReleaseDateByYear(spotifyAlbum.getReleaseDate());
            default -> throw new SpotifyChallengeException("Unknown date format");
        };
    }

    private static LocalDate getReleaseDateByMonth(String releaseDate) {
        String completeDate = releaseDate + "-01";
        return LocalDate.parse(completeDate);
    }

    private static LocalDate getReleaseDateByYear(String releaseDate) {
        String completeDate = releaseDate + "-01-01";
        return LocalDate.parse(completeDate);
    }
}
