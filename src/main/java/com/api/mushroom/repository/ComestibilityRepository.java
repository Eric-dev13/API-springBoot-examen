package com.api.mushroom.repository;

import com.api.mushroom.entity.EdibilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComestibilityRepository extends JpaRepository<EdibilityEntity, Long> {
}