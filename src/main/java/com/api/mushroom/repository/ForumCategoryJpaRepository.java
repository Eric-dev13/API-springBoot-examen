package com.api.mushroom.repository;

import com.api.mushroom.entity.ForumCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumCategoryJpaRepository extends JpaRepository<ForumCategoryEntity, Long> {
}