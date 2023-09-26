package com.api.mushroom.repository;

import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
l'annotation @Repository sur l'interface indique à Spring que cette interface est un bean de repository et
    que les exceptions DataAccessException qui se produisent au niveau de cette interface doivent être interceptées
    et traduites en exceptions plus spécifiques de Spring.
 */
@Repository
public interface MushroomJpaRepository extends JpaRepository<MushroomEntity, Long> {

    // Retourne la liste des champignons validé par l'admin ordonnée par nom commun
    // @Query("SELECT m FROM MushroomEntity m WHERE m.visibility = :visibility ORDER BY commonname")
    List<MushroomEntity> findAllByVisibility(boolean visibility);


    // TEST
    @Query("SELECT m FROM MushroomEntity m WHERE m.commonname = :commonname")
    List<MushroomEntity> getSearch(String commonname);
}
