package com.api.mushroom.repository;

import com.api.mushroom.entity.ForumSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ForumSubjectJpaRepository extends JpaRepository<ForumSubjectEntity, Long> {
    ArrayList<ForumSubjectEntity> findAll();
}