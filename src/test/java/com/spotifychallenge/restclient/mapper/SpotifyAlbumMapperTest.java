package com.spotifychallenge.restclient.mapper;

import com.spotifychallenge.exception.specific.AlbumMappingException;
import com.spotifychallenge.model.Album;
import com.spotifychallenge.restclient.dto.SpotifyAlbum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpotifyAlbumMapperTest {

    private static SpotifyAlbum getSpotifyAlbum() {
        SpotifyAlbum spotifyAlbum = new SpotifyAlbum();
        spotifyAlbum.setId("1To7kv722A8SpZF789MZy7");
        spotifyAlbum.setTitle("MTV Unplugged In New York");
        spotifyAlbum.setArtists(List.of(getArtist()));
        spotifyAlbum.setImages(List.of(getAlbumImage()));
        spotifyAlbum.setReleaseDate("1994-11-01");
        spotifyAlbum.setReleaseDatePrecision("day");
        spotifyAlbum.setDuration(14);
        return spotifyAlbum;
    }

    private static SpotifyAlbum.SpotifyArtist getArtist() {
        SpotifyAlbum.SpotifyArtist artist = new SpotifyAlbum.SpotifyArtist();
        artist.setId("6olE6TJLqED3rqDCT0FyPh");
        artist.setName("Nirvana");
        return artist;
    }

    private static SpotifyAlbum.SpotifyAlbumImage getAlbumImage() {
        SpotifyAlbum.SpotifyAlbumImage image = new SpotifyAlbum.SpotifyAlbumImage();
        image.setUrl("https://i.scdn.co/image/ab67616d0000b27336c5417732e53e23cb219246");
        image.setWidth(640);
        image.setHeight(640);
        return image;
    }

    @Test
    @DisplayName("it should return an album")
    void mapToModelTest() {
        // GIVEN
        SpotifyAlbum spotifyAlbum = getSpotifyAlbum();

        // WHEN
        Album album = SpotifyAlbumMapper.mapToModel(spotifyAlbum);

        // THEN
        assertEquals(spotifyAlbum.getId(), album.getId());
        assertEquals(spotifyAlbum.getTitle(), album.getTitle());
        assertEquals(spotifyAlbum.getReleaseDate(), album.getReleaseDate().toString());
        assertEquals(spotifyAlbum.getDuration(), album.getDuration());
        spotifyAlbum.getArtists().forEach(artist -> assertTrue(album.getArtists().contains(artist.getName())));
        assertTrue(spotifyAlbum.getImages().stream()
                .map(SpotifyAlbum.SpotifyAlbumImage::getUrl)
                .toList()
                .contains(album.getImage()));
    }

    @Test
    @DisplayName("it should throw an exception")
    void mapToModelExceptionTest() {
        // GIVEN
        SpotifyAlbum spotifyAlbum = getSpotifyAlbum();
        spotifyAlbum.setReleaseDatePrecision("unknown");

        // THEN
        assertThrowsExactly(AlbumMappingException.class, () -> {

            // WHEN
            SpotifyAlbumMapper.mapToModel(spotifyAlbum);
        });

    }
}
