package com.api.mushroom.service;

import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.repository.MushroomJpaRepository;
import com.api.mushroom.service.dto.MushroomDTO;
import com.api.mushroom.service.dto.UserDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
public class MushroomService {

    //private final MushroomRepository mushroomRepository;
    private final MushroomJpaRepository mushroomJpaRepository;

    // GET - Récupère un tableau d'enregistrement
    public Iterable<MushroomEntity> getAll() {
        return mushroomJpaRepository.findAll();
    }

    // GET - Récupère un enregistrement par l'ID
    public Optional<MushroomEntity> getById(Long id){
        return mushroomJpaRepository.findById(id);
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

    public List<MushroomEntity> findAllIsVisibility() {
        return mushroomJpaRepository.findAllIsVisibility();
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
