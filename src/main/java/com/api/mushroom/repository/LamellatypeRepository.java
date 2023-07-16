package com.api.mushroom.repository;

import com.api.mushroom.entity.LamellatypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LamellatypeRepository extends JpaRepository<LamellatypeEntity, Long> {
}