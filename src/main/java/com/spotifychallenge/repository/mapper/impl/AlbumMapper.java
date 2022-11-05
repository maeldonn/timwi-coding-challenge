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
        album.setAlbumId(model.getAlbumId());
        album.setTitle(model.getTitle());
        album.setDate(model.getDate());
        album.setDuration(model.getDuration());
        album.setFavorite(model.isFavorite());
        return album;
    }

    @Override
    public Album mapToModel(AlbumEntity entity) {
        return Album.builder()
            .albumId(entity.getAlbumId())
            .title(entity.getTitle())
            .date(entity.getDate())
            .duration(entity.getDuration())
            .favorite(entity.getFavorite())
            .build();
    }
}
