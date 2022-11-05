package com.spotifychallenge.repository;

import com.spotifychallenge.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagSpringDataRepository extends JpaRepository<Tag, String> {
}
