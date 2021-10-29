package com.api.mycologie.repository;

import com.api.mycologie.entity.MushroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MushroomRepository extends CrudRepository<MushroomEntity, Long> {

}