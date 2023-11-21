package com.api.mushroom.repository;

import com.api.mushroom.entity.ForumSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface ForumSubjectJpaRepository extends JpaRepository<ForumSubjectEntity, Long> {
    ArrayList<ForumSubjectEntity> findAll();

    @Query("SELECT f FROM ForumSubjectEntity f ORDER BY f.title LIMIT :limit OFFSET :offset")
    List<ForumSubjectEntity> findPaginate(Long limit, Long offset);

    @Query("SELECT COUNT(f) FROM ForumSubjectEntity f")
    Long countAllVisibleMushrooms();
}