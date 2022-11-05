package com.spotifychallenge.repository;

import com.spotifychallenge.entity.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumSpringDataRepository extends JpaRepository<AlbumEntity, String> {
}
