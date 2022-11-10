package com.spotifychallenge.repository.impl;

import com.spotifychallenge.entity.AlbumEntity;
import com.spotifychallenge.model.Album;
import com.spotifychallenge.repository.AlbumRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class AlbumRepositoryImplTest {

    @PersistenceContext
    private EntityManager entityManager;

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumRepositoryImplTest(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Test
    @DisplayName("it should return a list of albums")
    void getAlbumsTest() {
        // WHEN
        List<Album> albums = albumRepository.getAlbums();

        // THEN
        assertEquals(5, albums.size());
    }

    @Test
    @DisplayName("it should return an album")
    void findAlbumTest() {
        // GIVEN
        String albumId = "1To7kv722A8SpZF789MZy7";

        // WHEN
        Optional<Album> album = albumRepository.findAlbum(albumId);

        // THEN
        assertTrue(album.isPresent());
    }

    @Test
    @DisplayName("It should save an album")
    void addAlbumToPersonalListTest() {
        // GIVEN
        Album albumToSave = Album.builder()
                .id("5JY3b9cELQsoG7D5TJMOgw")
                .title("Multitude")
                .artists("Stromae")
                .image("https://i.scdn.co/image/ab67616d0000b27331d6b27ffaa0b2f89234698a")
                .releaseDate(LocalDate.of(2022, 3, 3))
                .duration(12)
                .build();

        // WHEN
        Album album = albumRepository.addAlbumToPersonalList(albumToSave);

        // THEN
        assertTrue(album.isPersonal());
    }

    @Test
    @DisplayName("It should delete an album")
    void removeAlbumFromPersonalListTest() {
        // GIVEN
        String albumId = "2GMizsQeKeilPBjUjrfqhI";

        // WHEN
        albumRepository.removeAlbumFromPersonalList(albumId);
        entityManager.flush();

        // THEN
        assertThrows(EntityNotFoundException.class, entityManager.getReference(AlbumEntity.class, albumId)::toString);
    }

    @Test
    @DisplayName("it should toggle favorite on an album")
    void toggleFavoriteAlbumTest() {
        // GIVEN
        String albumId = "2GMizsQeKeilPBjUjrfqhI";

        // WHEN
        Album album = albumRepository.toggleFavoriteAlbum(albumId);

        // THEN
        assertEquals(Boolean.TRUE, album.isFavorite());
    }
}
