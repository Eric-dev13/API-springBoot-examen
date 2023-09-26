package com.api.mushroom.repository;

import com.api.mushroom.entity.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaJpaRepository extends JpaRepository<MediaEntity, Long> {
}