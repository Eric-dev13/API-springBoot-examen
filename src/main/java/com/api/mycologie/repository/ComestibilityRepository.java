package com.api.mycologie.repository;

import com.api.mycologie.entity.EdibilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComestibilityRepository extends JpaRepository<EdibilityEntity, Long> {
}