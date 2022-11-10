package com.spotifychallenge.service.impl;

import com.spotifychallenge.model.Album;
import com.spotifychallenge.repository.AlbumRepository;
import com.spotifychallenge.restclient.SpotifyRestClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlbumServiceImplTest {

    @Mock
    private SpotifyRestClient restClient;

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumServiceImpl albumService;

    @Test
    @DisplayName("it should return a list of albums")
    void getAlbumsTest() {
        // GIVEN
        String searchFilter = "Nirvana";
        Album albumFromSpotify = Album.builder()
                .id("1To7kv722A8SpZF789MZy7")
                .title("MTV Unplugged In New York")
                .artists("Nirvana")
                .image("https://i.scdn.co/image/ab67616d0000b27336c5417732e53e23cb219246")
                .releaseDate(LocalDate.of(1994, 11, 1))
                .duration(14)
                .build();
        Album albumFromDatabase = Album.builder()
                .id("1To7kv722A8SpZF789MZy7")
                .title("MTV Unplugged In New York")
                .artists("Nirvana")
                .image("https://i.scdn.co/image/ab67616d0000b27336c5417732e53e23cb219246")
                .releaseDate(LocalDate.of(1994, 11, 1))
                .duration(14)
                .personal(true)
                .favorite(false)
                .tags(Collections.emptyList())
                .build();

        // MOCK
        when(restClient.searchAlbums(any())).thenReturn(List.of(albumFromSpotify));
        when(albumRepository.findAlbum(any())).thenReturn(Optional.of(albumFromDatabase));

        // WHEN
        List<Album> albums = albumService.getAlbums(searchFilter);

        // THEN
        assertEquals(Boolean.TRUE, albums.get(0).isPersonal());
    }

    @Test
    @DisplayName("It should return personals albums")
    void getPersonalAlbumsTest() {
        // GIVEN
        Album albumFromDatabase = Album.builder()
                .id("1To7kv722A8SpZF789MZy7")
                .title("MTV Unplugged In New York")
                .artists("Nirvana")
                .image("https://i.scdn.co/image/ab67616d0000b27336c5417732e53e23cb219246")
                .releaseDate(LocalDate.of(1994, 11, 1))
                .duration(14)
                .personal(true)
                .favorite(false)
                .tags(Collections.emptyList())
                .build();

        // MOCK
        when(albumRepository.getAlbums()).thenReturn(List.of(albumFromDatabase));

        // WHEN
        List<Album> albums = albumService.getPersonalAlbums();

        // THEN
        albums.forEach(album -> assertTrue(album.isPersonal()));
    }

    @Test
    @DisplayName("It should create a new album")
    void addAlbumToPersonalListTest() {
        // GIVEN
        String albumId = "1To7kv722A8SpZF789MZy7";
        Album albumToSave = Album.builder()
                .id("1To7kv722A8SpZF789MZy7")
                .title("MTV Unplugged In New York")
                .artists("Nirvana")
                .image("https://i.scdn.co/image/ab67616d0000b27336c5417732e53e23cb219246")
                .releaseDate(LocalDate.of(1994, 11, 1))
                .duration(14)
                .build();
        Album savedAlbum = Album.builder()
                .id("1To7kv722A8SpZF789MZy7")
                .title("MTV Unplugged In New York")
                .artists("Nirvana")
                .image("https://i.scdn.co/image/ab67616d0000b27336c5417732e53e23cb219246")
                .releaseDate(LocalDate.of(1994, 11, 1))
                .duration(14)
                .personal(true)
                .favorite(false)
                .tags(Collections.emptyList())
                .build();

        // MOCK
        when(restClient.searchAlbum(any())).thenReturn(albumToSave);
        when(albumRepository.addAlbumToPersonalList(any())).thenReturn(savedAlbum);

        // WHEN
        Album album = albumService.addAlbumToPersonalList(albumId);

        // THEN
        assertEquals(albumId, album.getId());
    }

    @Test
    @DisplayName("It should toggle a favorite album")
    void toggleFavoriteAlbumTest() {
        // GIVEN
        String albumId = "1To7kv722A8SpZF789MZy7";
        Album updatedAlbum = Album.builder()
                .id("1To7kv722A8SpZF789MZy7")
                .title("MTV Unplugged In New York")
                .artists("Nirvana")
                .image("https://i.scdn.co/image/ab67616d0000b27336c5417732e53e23cb219246")
                .releaseDate(LocalDate.of(1994, 11, 1))
                .duration(14)
                .personal(true)
                .favorite(false)
                .tags(Collections.emptyList())
                .build();

        // MOCK
        when(albumRepository.toggleFavoriteAlbum(any())).thenReturn(updatedAlbum);

        // WHEN
        Album album = albumService.toggleFavoriteAlbum(albumId);

        // THEN
        assertEquals(albumId, album.getId());
    }
}
