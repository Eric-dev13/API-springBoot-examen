package com.api.mushroom.repository;

import com.api.mushroom.entity.ForumSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface ForumSubjectJpaRepository extends JpaRepository<ForumSubjectEntity, Long> {
    ArrayList<ForumSubjectEntity> findAll();

    @Query("SELECT f FROM ForumSubjectEntity f ORDER BY f.createdAt DESC LIMIT :limit OFFSET :offset")
    List<ForumSubjectEntity> findAllPaginate(@Param("limit") Long limit, @Param("offset") Long offset);

    @Query("SELECT COUNT(f) FROM ForumSubjectEntity f")
    Long countAllForumSubject();

    @Query("SELECT f FROM ForumSubjectEntity f JOIN f.forumCategories fc WHERE fc.id = :categoryId ORDER BY f.createdAt DESC LIMIT :limit OFFSET :offset")
    List<ForumSubjectEntity> findPaginateAndFilterCategory(@Param("limit") Long limit, @Param("offset") Long offset, @Param("categoryId") Long categoryId);

    @Query("SELECT COUNT(f) FROM ForumSubjectEntity f JOIN f.forumCategories fc WHERE fc.id = :categoryId")
    Long countTotalForumSubjectsByCategory(@Param("categoryId") Long categoryId);


}