package com.spotifychallenge.repository;

import com.spotifychallenge.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagSpringDataRepository extends JpaRepository<TagEntity, String> {
}
