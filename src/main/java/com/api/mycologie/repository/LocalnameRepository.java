package com.api.mycologie.repository;

import com.api.mycologie.entity.LocalnameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalnameRepository extends JpaRepository<LocalnameEntity, Long> {
}