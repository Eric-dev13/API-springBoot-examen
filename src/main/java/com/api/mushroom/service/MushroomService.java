package com.api.mushroom.service;

import com.api.mushroom.entity.MediaEntity;
import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.repository.MushroomJpaRepository;
import com.api.mushroom.service.dto.MushroomDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
public class MushroomService {

    private final MushroomJpaRepository mushroomJpaRepository; // Créer une instance de mushroomJpaRepository via le constructeur
    private final EntityManager entityManager; // Créer une instance de l'EntityManager via le constructeur

    // GET - Récupère un tableau d'enregistrement
    public Iterable<MushroomEntity> getAll() {
        return mushroomJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "commonname"));
    }

    // GET - Retourne un tableau d'objets - liste de tous les enregistrements validés par l'administrateur pour la publication.
    public List<MushroomEntity> findAllByVisibility(boolean isVisible) {
        return mushroomJpaRepository.findAllByVisibility(isVisible);
    }

    // GET - Récupère un enregistrement par l'ID
    public Optional<MushroomEntity> getById(Long id){
        return mushroomJpaRepository.findById(id);
    }

    // POST : Ajouter un enregistrement
    public MushroomEntity add(@RequestBody MushroomEntity mushroomEntity) {
        for (MediaEntity media : mushroomEntity.getMedias()) {
            //Renommer le fichier

            // Upload le fichier

             // Associez chaque média à l'entité MushroomEntity
            media.setMushroomEntity(mushroomEntity);
        }
        return mushroomJpaRepository.save(mushroomEntity);
    }

    // UPDATE : Mettre à jour un enregistrement
    public MushroomEntity put(@RequestBody MushroomEntity mushroomEntity){
        return mushroomJpaRepository.save(mushroomEntity);
    }

    // UPDATE : Mettre à jour un enregistrement
    public MushroomEntity patch(@RequestBody MushroomEntity mushroomEntity){

        return mushroomJpaRepository.save(mushroomEntity);
    }

    // delete : Supprimer un enregistrement
    public void delete(Long id) {
        mushroomJpaRepository.deleteById(id);
    }

    // inverse l'état booleen du champ visibility
    public void invertPublish(Long id){
        mushroomJpaRepository.findById(id)
                        .map(mushroom -> {
                            boolean isVisible = mushroom.isVisibility();
                            mushroom.setVisibility(!isVisible);
                            return mushroomJpaRepository.save(mushroom);
                        });
    }

    //TEST
    public List<MushroomEntity> getSearch(String titre) {
        return mushroomJpaRepository.getSearch(titre);
    }

/* ----------------------------------------------------------------------------------------------------------------------- */

    // GET - Retourne une liste de tableau de 3 propriétés.
    public List findAllByVisibilityWithTitleImageEdibility () {
        return entityManager
                .createNamedQuery("MushroomEntity.findAllTitleImageEdibilityByVisibility")
                .setParameter("visibility", true)
                .getResultList();
    }

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




    /*
    // Exemple avec mappage des données (forunit par le DAO: MushroomJpaRepository)  vers un objet DTO.
    public MushroomDTO getMushroomById(Long id) {
        MushroomEntity mushroomEntity = mushroomJpaRepository.findById(id).orElse(null);
        return convertToDTO(mushroomEntity);
    }

    // Autres méthodes pour effectuer des opérations métier avec les utilisateurs
    // ...

    private MushroomDTO convertToDTO(MushroomEntity mushroomEntity) {
        // instancie un nouvel mushroomDTO pour chaque objet renvoyé par le DAO.
        MushroomDTO mushroomDTO = new MushroomDTO();

        mushroomDTO.setComment(mushroomEntity.getComment());
        mushroomDTO.setId(mushroomEntity.getId());
        mushroomDTO.setCreatedAt(mushroomEntity.getCreatedAt());
        mushroomDTO.setUpdatedAt(mushroomEntity.getUpdatedAt());
        //mushroomDTO.setVisibility(mushroomEntity.getVisibility());
        mushroomDTO.setCommonname(mushroomEntity.getCommonname());
        mushroomDTO.setLatinname(mushroomEntity.getLatinname());
        mushroomDTO.setHat(mushroomEntity.getHat());
        mushroomDTO.setLamella(mushroomEntity.getLamella());
        mushroomDTO.setFoot(mushroomEntity.getFoot());
        mushroomDTO.setHabitat(mushroomEntity.getHabitat());
        mushroomDTO.setComment(mushroomEntity.getComment());
        mushroomDTO.setSlug(mushroomEntity.getSlug());

        return mushroomDTO;
    }
     */
}
