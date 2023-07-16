package com.api.mushroom.repository;

import com.api.mushroom.entity.MushroomEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MushroomRepository extends CrudRepository<MushroomEntity, Long> {

}