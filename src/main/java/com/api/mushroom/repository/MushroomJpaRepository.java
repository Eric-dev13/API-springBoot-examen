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
public interface
MushroomJpaRepository extends JpaRepository<MushroomEntity, Long> {

    // Retourne la liste des champignons validés par l'administrateur ordonnée par nom commun
    @Query("SELECT m FROM MushroomEntity m WHERE m.visibility = true ORDER BY m.commonname")
    List<MushroomEntity> findAllByVisibility();

    @Query("SELECT m FROM MushroomEntity m WHERE m.visibility = true ORDER BY m.commonname LIMIT :limit OFFSET :offset")
    List<MushroomEntity> findAllByVisibilityPaginate( @Param("limit") Long limit,  @Param("offset") Long offset);

    @Query("SELECT COUNT(m) FROM MushroomEntity m WHERE m.visibility = true")
    Long countAllVisibleMushrooms();


    // REQUETE SQL ADMINISTRATEUR
    @Query("SELECT m FROM MushroomEntity m ORDER BY m.commonname")
    List<MushroomEntity> findAll();

    @Query("SELECT m FROM MushroomEntity m ORDER BY m.commonname LIMIT :limit OFFSET :offset")
    List<MushroomEntity> findAllPaginate( @Param("limit") Long limit,  @Param("offset") Long offset);

    @Query("SELECT COUNT(m) FROM MushroomEntity m")
    Long countAllMushrooms();


    // TEST
//    @Query("SELECT m FROM MushroomEntity m WHERE m.commonname = :commonname")
//    List<MushroomEntity> getSearch(String commonname);
}
