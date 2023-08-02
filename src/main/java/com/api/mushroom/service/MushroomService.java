package com.api.mushroom.service;

import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.repository.MushroomJpaRepository;
import com.api.mushroom.service.dto.MushroomDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
public class MushroomService {

    private final MushroomJpaRepository mushroomJpaRepository; // MushroomJpaRepository intancié via le constructeur

    private final EntityManager entityManager; // EntityManager intancié via le constructeur

    // GET - Récupère un tableau d'enregistrement
    public Iterable<MushroomEntity> getAll() {
        return mushroomJpaRepository.findAll();
    }

    // GET - Retourne un tableau d'objets - liste de tous les enregisterments validé par l'administrateur pour la publication.
    public List<MushroomEntity> findAllByVisibility(boolean isVisible) {
        return mushroomJpaRepository.findAllByVisibility(isVisible);
    }

    // GET - Retourne une liste de tableau de 3 propriétés.
    public List findAllByVisibilityWithTitleImageEdibility () {
        return entityManager
            .createNamedQuery("MushroomEntity.findAllTitleImageEdibilityByVisibility")
            .setParameter("visibility", true)
            .getResultList();
    }

    // GET - Récupère un enregistrement par l'ID
    public Optional<MushroomEntity> getById(Long id){
        return mushroomJpaRepository.findById(id);
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

    // POST : Ajouter un enregistrement
    public MushroomEntity add(@RequestBody MushroomEntity mushroomEntity) {
        return mushroomJpaRepository.save(mushroomEntity);
    }

    // UPDATE : Mettre à jour un enregistrement
    public MushroomEntity edit(@RequestBody MushroomEntity mushroomEntity){
        return mushroomJpaRepository.save(mushroomEntity);
    }

    // delete : Supprimer un enregistrement
    public void delete(Long id) {
        mushroomJpaRepository.deleteById(id);
    }



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
}
