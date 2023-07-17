package com.api.mushroom.repository;

import com.api.mushroom.entity.MushroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MushroomJpaRepository extends JpaRepository<MushroomEntity, Long> {

}
