package com.api.mushroom.repository;

import com.api.mushroom.entity.ForumCommentaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumCommentaryJpaRepository extends JpaRepository<ForumCommentaryEntity, Long> {
}