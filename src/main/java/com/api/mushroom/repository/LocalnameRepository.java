package com.api.mushroom.repository;

import com.api.mushroom.entity.LocalnameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalnameRepository extends JpaRepository<LocalnameEntity, Long> {
}