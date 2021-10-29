package com.api.mycologie.repository;

import com.api.mycologie.entity.LamellatypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LamellatypeRepository extends JpaRepository<LamellatypeEntity, Long> {
}