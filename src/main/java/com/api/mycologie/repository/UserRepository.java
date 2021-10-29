package com.api.mycologie.repository;

import com.api.mycologie.entity.MushroomEntity;
import com.api.mycologie.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    // Methodes pour recuperer le premier enregistrement d'une table apres un save ou un update par exemple
    MushroomEntity findTopByOrderByIdAsc();

    // Récupère le dernier enregistrement d'une table (apres un save ou un update).
    MushroomEntity findTopByOrderByIdDesc();
    MushroomEntity findFirstByOrderByIdDesc();
}