package com.spotifychallenge.repository.mapper;


import java.util.List;

public interface SpotifyChallengeMapper<M, E> {

    M mapToModel(final E entity); 

    E mapToEntity(final M model); 

    default List<M> mapToModelList(List<E> entities) {
        return entities.stream().map(this::mapToModel).toList();
    }
        
    default List<E> mapToEntityList(List<M> models) {
        return models.stream().map(this::mapToEntity).toList();
    }
}
