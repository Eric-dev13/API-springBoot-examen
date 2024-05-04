package com.api.mushroom.service;

import com.api.mushroom.Mapper.MapStructMapper;
import com.api.mushroom.entity.LocalnameEntity;
import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.repository.MushroomJpaRepository;
import com.api.mushroom.service.model.MushroomServiceModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MushroomService {

    private final MushroomJpaRepository mushroomJpaRepository;
    private final EntityManager entityManager;
    private final MapStructMapper mapStructMapper;

    /* --------------------------------------------------------------- */
    /*                          ROUTE - PUBLIQUE                       */
    /* --------------------------------------------------------------- */

    public List<MushroomServiceModel> findAllFilter(Long limit, Long offset) {
        List<MushroomEntity> mushroomEntities ;
        if (limit == null) {
            mushroomEntities = mushroomJpaRepository.findAllByVisibility();
        } else {
            mushroomEntities = mushroomJpaRepository.findAllByVisibilityPaginate(limit, offset);
        }
        List<MushroomServiceModel> mushrooms =  mushroomEntities.stream().map(mapStructMapper::mushroomEntityToMushroomService).collect(Collectors.toList());
        return mushrooms;
    }

    public Long countAllVisibleMushrooms() {
        return mushroomJpaRepository.countAllVisibleMushrooms();
    }

    public MushroomServiceModel getById(Long id) throws RoleNotFoundException {
        MushroomEntity mushroom = mushroomJpaRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("La fiche avec l'ID spécifié n'a pas été trouvé."));
        return mapStructMapper.mushroomEntityToMushroomService(mushroom);
    }

    public MushroomServiceModel findBySlug(String slug){
        MushroomEntity result = entityManager
                .createNamedQuery("MushroomEntity.findBySlug", MushroomEntity.class)
                .setParameter("slug", slug)
                .getSingleResult();
        return mapStructMapper.mushroomEntityToMushroomService(result);
    }

    /* --------------------------------------------------------------- */
    /*                          ROUTE - SECURISER                      */
    /* --------------------------------------------------------------- */
    public List<MushroomServiceModel> findAllFilterAdmin(Long limit, Long offset) {

        List<MushroomEntity> mushroomEntities ;
        if (limit == null) {
            mushroomEntities = mushroomJpaRepository.findAll();
        } else {
            mushroomEntities = mushroomJpaRepository.findAllPaginate(limit, offset);
        }
        List<MushroomServiceModel> mushrooms = mushroomEntities.stream().map(mapStructMapper::mushroomEntityToMushroomService).collect(Collectors.toList());

        return mushrooms;
    }

    public Long countAllMushrooms() {
        return mushroomJpaRepository.countAllMushrooms();
    }

    public Long add(MushroomServiceModel mushroomServiceModel) {
        MushroomEntity mushroomEntity = mapStructMapper.mushroomServiceToMushroomEntity(mushroomServiceModel);
        for (LocalnameEntity localname : mushroomEntity.getLocalnames()) {
            // Associe chaque enregistrement "nom local" nouvellement crée à l'entité MushroomEntity
            localname.setMushroom(mushroomEntity);
            // PERSISTE EN BASE DE DONNEE - ici pas besoin de persister, car on a, définit, cascade = CascadeType.PERSIST dans MushroomEntity pour cette propriété.
            // localnameJpaRepository.save(localname);
        }
        MushroomEntity savedMushroomEntity = mushroomJpaRepository.save(mushroomEntity);
        return savedMushroomEntity.getId();
    }

    public Long put(Long id, MushroomServiceModel mushroomServiceModel){
        MushroomEntity mushroomEntity = mapStructMapper.mushroomServiceToMushroomEntity(mushroomServiceModel);
        MushroomEntity mushroomById = mushroomJpaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Mushroom not found"));
        mushroomById.setCommonname(mushroomEntity.getCommonname());
        mushroomById.setFlesh(mushroomEntity.getFlesh());
        mushroomById.setHat(mushroomEntity.getHat());
        mushroomById.setLamella(mushroomEntity.getLamella());
        mushroomById.setFoot(mushroomEntity.getFoot());
        mushroomById.setHabitat(mushroomEntity.getHabitat());
        mushroomById.setComment(mushroomEntity.getComment());
        if (mushroomEntity.getLamellatype() != null) {
            mushroomById.setLamellatype(mushroomEntity.getLamellatype());
        }
        if (mushroomEntity.getEdibility() != null) {
            mushroomById.setEdibility(mushroomEntity.getEdibility());
        }
        // traite un tableau
//        if (mushroomEntity.getLocalnames() != null) {
//            mushroomById.setLamellatype(mushroomEntity.getLamellatype());
//        }
        // On ne gere pas les médias ici
        MushroomEntity savedMushroomEntity = mushroomJpaRepository.save(mushroomById);
        return savedMushroomEntity.getId();
    }

    public boolean delete(Long id) {
        if(mushroomJpaRepository.existsById(id)) {
            mushroomJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Inverse la valeur booléen du champ visibility
    public boolean invertPublish(Long id){
        Optional<MushroomEntity> mushroomEntity = mushroomJpaRepository.findById(id);
        if (mushroomEntity.isPresent()){
            MushroomEntity mushroom = mushroomEntity.get();
            boolean isVisible = mushroom.isVisibility();
            mushroom.setVisibility(!isVisible);
            MushroomEntity updatedMushroom = mushroomJpaRepository.save(mushroom);
            return updatedMushroom != null; // on sait que != null car .isPresent() est true
        }
        return false;
    }

}
