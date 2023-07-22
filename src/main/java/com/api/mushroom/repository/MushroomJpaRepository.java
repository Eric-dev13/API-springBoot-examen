package com.api.mushroom.repository;

import com.api.mushroom.entity.MushroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
l'annotation @Repository sur l'interface indique à Spring que cette interface est un bean de repository et
que les exceptions DataAccessException qui se produisent au niveau de cette interface doivent être interceptées et traduites en exceptions plus spécifiques de Spring.
 */
@Repository
public interface MushroomJpaRepository extends JpaRepository<MushroomEntity, Long> {

}
