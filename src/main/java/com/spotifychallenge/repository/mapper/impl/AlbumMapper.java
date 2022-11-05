package com.spotifychallenge.repository.mapper.impl;


import com.spotifychallenge.dto.AlbumDto;
import com.spotifychallenge.model.Album;
import com.spotifychallenge.repository.mapper.SpotifyChallengeMapper;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper implements SpotifyChallengeMapper<AlbumDto, Album> {

    @Override
    public Album mapToEntity(AlbumDto model) {
        Album album = new Album();
        album.setAlbumId(model.getAlbumId());
        album.setTitle(model.getTitle());
        album.setDate(model.getDate());
        album.setDuration(model.getDuration());
        album.setFavorite(model.isFavorite());
        return album;
    }

    @Override
    public AlbumDto mapToModel(Album entity) {
        return AlbumDto.builder()
            .albumId(entity.getAlbumId())
            .title(entity.getTitle())
            .date(entity.getDate())
            .duration(entity.getDuration())
            .favorite(entity.getFavorite())
            .build();
    }
}
