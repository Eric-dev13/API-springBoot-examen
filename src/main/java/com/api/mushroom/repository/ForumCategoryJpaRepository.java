package com.api.mushroom.repository;

import com.api.mushroom.entity.ForumCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumCategoryJpaRepository extends JpaRepository<ForumCategory, Long> {
}