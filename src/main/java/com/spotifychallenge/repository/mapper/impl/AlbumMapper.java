package com.spotifychallenge.repository.mapper.impl;


import com.spotifychallenge.model.Album;
import com.spotifychallenge.entity.AlbumEntity;
import com.spotifychallenge.repository.mapper.SpotifyChallengeMapper;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper implements SpotifyChallengeMapper<Album, AlbumEntity> {

    private final TagMapper tagMapper;

    public AlbumMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    public AlbumEntity mapToEntity(Album model) {
        AlbumEntity album = new AlbumEntity();
        album.setId(model.getId());
        album.setTitle(model.getTitle());
        album.setArtists(model.getArtists());
        album.setImage(model.getImage());
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
                .artists(entity.getArtists())
                .image(entity.getImage())
                .releaseDate(entity.getReleaseDate())
                .duration(entity.getDuration())
                .personal(Boolean.TRUE)
                .favorite(entity.getFavorite())
                .tags(tagMapper.mapToModelList(entity.getTags()))
                .build();
    }
}
