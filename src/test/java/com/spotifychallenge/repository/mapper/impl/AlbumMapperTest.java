package com.spotifychallenge.repository.mapper.impl;

import com.spotifychallenge.entity.AlbumEntity;
import com.spotifychallenge.model.Album;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlbumMapperTest {

    @Mock
    private TagMapper tagMapper;

    @InjectMocks
    private AlbumMapper mapper;

    private static Album getModel() {
        return Album.builder()
                .id("1To7kv722A8SpZF789MZy7")
                .title("MTV Unplugged In New York")
                .artists("Nirvana")
                .image("https://i.scdn.co/image/ab67616d0000b27336c5417732e53e23cb219246")
                .releaseDate(LocalDate.of(1994, 11, 1))
                .duration(14)
                .personal(true)
                .favorite(true)
                .tags(Collections.emptyList())
                .build();
    }

    @Test
    @DisplayName("it should return an entity")
    void mapToEntityTest() {
        // GIVEN
        Album model = getModel();

        // WHEN
        AlbumEntity entity = mapper.mapToEntity(model);

        // THEN
        assertEquals(model.getId(), entity.getId());
        assertEquals(model.getTitle(), entity.getTitle());
        assertEquals(model.getArtists(), entity.getArtists());
        assertEquals(model.getImage(), entity.getImage());
        assertEquals(model.getReleaseDate(), entity.getReleaseDate());
        assertEquals(model.getDuration(), entity.getDuration());
        assertEquals(model.isFavorite(), entity.getFavorite());
    }

    private static AlbumEntity getEntity() {
        AlbumEntity entity = new AlbumEntity();
        entity.setId("1To7kv722A8SpZF789MZy7");
        entity.setTitle("MTV Unplugged In New York");
        entity.setArtists("Nirvana");
        entity.setImage("https://i.scdn.co/image/ab67616d0000b27336c5417732e53e23cb219246");
        entity.setReleaseDate(LocalDate.of(1994, 11, 1));
        entity.setDuration(14);
        entity.setFavorite(true);
        entity.setTags(Collections.emptyList());
        return entity;
    }

    @Test
    @DisplayName("it should return a model")
    void mapToModelTest() {
        // GIVEN
        AlbumEntity entity = getEntity();

        // MOCK
        when(tagMapper.mapToModelList(any())).thenReturn(Collections.emptyList());

        // WHEN
        Album model = mapper.mapToModel(entity);

        // THEN
        assertEquals(entity.getId(), model.getId());
        assertEquals(entity.getTitle(), model.getTitle());
        assertEquals(entity.getArtists(), model.getArtists());
        assertEquals(entity.getImage(), model.getImage());
        assertEquals(entity.getReleaseDate(), model.getReleaseDate());
        assertEquals(entity.getDuration(), model.getDuration());
        assertEquals(entity.getFavorite(), model.isFavorite());
        assertEquals(Boolean.TRUE, model.isPersonal());
    }
}
