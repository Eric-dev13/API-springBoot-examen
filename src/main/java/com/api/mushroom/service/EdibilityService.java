package com.api.mushroom.service;

import com.api.mushroom.entity.EdibilityEntity;
import com.api.mushroom.entity.LamellatypeEntity;
import com.api.mushroom.repository.EdibilityJpaRepository;
import com.api.mushroom.repository.LamellaTypeJpaRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
public class EdibilityService {
    private final EdibilityJpaRepository edibilityJpaRepository;

    // GET - Récupère un tableau d'enregistrement
    public Iterable<EdibilityEntity> getAll() {
        return edibilityJpaRepository.findAll();
    }

    // GET - Récupère un enregistrement par l'ID
    public Optional<EdibilityEntity> getById(Long id){
        return edibilityJpaRepository.findById(id);
    }

    // POST : Ajouter un enregistrement
    public EdibilityEntity add(@RequestBody EdibilityEntity edibilityEntity) {
        return edibilityJpaRepository.save(edibilityEntity);
    }

    // UPDATE : Mettre à jour un enregistrement
    public EdibilityEntity edit(@RequestBody EdibilityEntity edibilityEntity){
        return edibilityJpaRepository.save(edibilityEntity);
    }

    // delete : Supprimer un enregistrement
    public void delete(Long id) {
        edibilityJpaRepository.deleteById(id);
    }
}
