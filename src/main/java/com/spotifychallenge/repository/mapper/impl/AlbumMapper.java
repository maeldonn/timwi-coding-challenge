package com.spotifychallenge.repository.mapper.impl;


import com.spotifychallenge.model.Album;
import com.spotifychallenge.entity.AlbumEntity;
import com.spotifychallenge.repository.mapper.SpotifyChallengeMapper;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper implements SpotifyChallengeMapper<Album, AlbumEntity> {

    @Override
    public AlbumEntity mapToEntity(Album model) {
        AlbumEntity album = new AlbumEntity();
        album.setId(model.getId());
        album.setTitle(model.getTitle());
        album.setReleaseDate(model.getReleaseDate());
        album.setDuration(model.getDuration());
        album.setFavorite(model.isFavorite());
        return album;
    }

    @Override
    public Album mapToModel(AlbumEntity entity) {
        return Album.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .releaseDate(entity.getReleaseDate())
                .duration(entity.getDuration())
                .favorite(entity.getFavorite())
                .build();
    }
}
