package com.api.mushroom.service;

import com.api.mushroom.entity.LocalnameEntity;
import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.repository.LocalnameJpaRepository;
import com.api.mushroom.repository.MushroomJpaRepository;
import com.api.mushroom.service.utils.FileUploadService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MushroomService {

    private final MushroomJpaRepository mushroomJpaRepository; // Créer une instance de mushroomJpaRepository
    private final EntityManager entityManager; // Créer une instance de l'EntityManager
    private final FileUploadService fileUploadService;
    private final LocalnameJpaRepository localnameJpaRepository;

    /* --------------------------------------------------------------- */
    /*                          ROUTE - PUBLIQUE                       */
    /* --------------------------------------------------------------- */

    // GET - Retourne une partie des fiches descriptives marqués comme visible.
    public List<MushroomEntity> findAllByVisibility() {
        return mushroomJpaRepository.findAllByVisibility();
    }

    // GET - Retourne une partie des fiches descriptives marqués comme visible.
    public List<MushroomEntity> findAllByVisibilityPaginate(Long limit, Long offset) {
        return mushroomJpaRepository.findAllByVisibilityPaginate(limit, offset);
    }

    // Retourne le nombre d'enregistrement de la table marqués comme visible.
    public Long countAllVisibleMushrooms() {
        return mushroomJpaRepository.countAllVisibleMushrooms();
    }

    // GET - Récupère un enregistrement par l'ID
    public Optional<MushroomEntity> getById(Long id){
        return mushroomJpaRepository.findById(id);
    }


    /* --------------------------------------------------------------- */
    /*                          ROUTE - SECURISER                      */
    /* --------------------------------------------------------------- */
    // GET - Récupère un tableau d'enregistrement trié par nom commun
    public List<MushroomEntity> findAll() {
        return mushroomJpaRepository.findAll();
    }

    public List<MushroomEntity> findAllPaginate(Long limit, Long offset) {
        return mushroomJpaRepository.findAllPaginate(limit, offset);
    }

    // Retourne le nombre d'enregistrement de la table.
    public Long countAllMushrooms() {
        return mushroomJpaRepository.countAllMushrooms();
    }

    // POST : Ajouter un enregistrement
    public MushroomEntity add(MushroomEntity mushroomEntity) {
//        // Les opérations de persistance vont créer automatiquement les enregistrements dans les tables, il faut ajouter la cle étrangère pour la liaison.
//        for (MediaEntity media : mushroomEntity.getMedias()) {
//            // Renommer le fichier
//            // Upload le fichier
//            // Associe chaque média nouvellement crée à l'entité MushroomEntity (RENSIGNE LA CLE ETRANGERE)
//            media.setMushroomEntity(mushroomEntity);
//            // PERSISTE EN BASE DE DONNEE /
//            //mediaJpaRepository.save(media);
//        }
        for (LocalnameEntity localname : mushroomEntity.getLocalnames()) {
            // Associe chaque enregistrement "nom local" nouvellement crée à l'entité MushroomEntity
            localname.setMushroom(mushroomEntity);
            // PERSISTE EN BASE DE DONNEE - ici pas besoin de persister, car on a, définit, cascade = CascadeType.PERSIST dans MushroomEntity pour cette propriété.
            // localnameJpaRepository.save(localname);
        }
        return mushroomJpaRepository.save(mushroomEntity);
    }

    // UPDATE : Mettre à jour un enregistrement
    public MushroomEntity put(Long id, MushroomEntity mushroomEntity){

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
        return mushroomJpaRepository.save(mushroomById);
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

//    public boolean invertPublish1(Long id){
//        return mushroomJpaRepository.findById(id)
//                .map(mushroom -> {
//                    boolean isVisible = mushroom.isVisibility();
//                    mushroom.setVisibility(!isVisible);
//                    MushroomEntity updatedMushroom = mushroomJpaRepository.save(mushroom);
//                    return updatedMushroom != null;
//                })
//                .orElse(false);
//    }



    // PATCH : Mise à jour partiel d'un enregistrement
//    public MushroomEntity patch(Long id, MushroomEntity mushroomEntity) {
//        MushroomEntity mushroomById = mushroomJpaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Mushroom not found"));
//
//        mushroomById.setCommonname(mushroomEntity.getCommonname());
//        mushroomById.setLatinname(mushroomEntity.getLatinname());
//        mushroomById.setFlesh(mushroomEntity.getFlesh());
//        mushroomById.setHat(mushroomEntity.getHat());
//        mushroomById.setLamella(mushroomEntity.getLamella());
//        mushroomEntity.setFoot(mushroomEntity.getFoot());
//        mushroomById.setHabitat(mushroomEntity.getHabitat());
//        mushroomById.setComment(mushroomEntity.getComment());
//
//        if (mushroomEntity.getLamellatype() != null) {
//            mushroomById.setLamellatype(mushroomEntity.getLamellatype());
//        }
//
//        if (mushroomEntity.getEdibility() != null) {
//            mushroomById.setEdibility(mushroomEntity.getEdibility());
//        }
//
//        return mushroomJpaRepository.save(mushroomById);
//    }




    /* --------------------------------------------------------------------------------------------- */
    public Optional<MushroomEntity> findBySlug(String slug){
        try {
            MushroomEntity result = entityManager
                    .createNamedQuery("MushroomEntity.findBySlug", MushroomEntity.class)
                    .setParameter("slug", slug)
                    .getSingleResult();
            return Optional.of(result);
        } catch (
                NoResultException ex) {
            return Optional.empty();
        }
    }

//    public List<MushroomEntity> getSearch(String titre) {
//        return mushroomJpaRepository.getSearch(titre);
//    }
    /* --------------------------------------------------------------------------------------------- */

    // GET - Retourne une liste de tableau de 3 propriétés.
//    public List findAllByVisibilityWithTitleImageEdibility () {
//        return entityManager
//                .createNamedQuery("MushroomEntity.findAllTitleImageEdibilityByVisibility")
//                .setParameter("visibility", true)
//                .getResultList();
//    }



}
