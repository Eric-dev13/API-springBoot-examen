package com.api.mushroom.service;

import com.api.mushroom.entity.EdibilityEntity;
import com.api.mushroom.entity.LamellatypeEntity;
import com.api.mushroom.repository.LamellaTypeJpaRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@RequiredArgsConstructor
//@Data
@Service
public class LamellaTypeService {

    private final LamellaTypeJpaRepository lamellaTypeJpaRepository;

    // GET - Récupère un tableau d'enregistrement
    public Iterable<LamellatypeEntity> getAll() {
        return lamellaTypeJpaRepository.findAll();
    }

    // GET - Récupère un enregistrement par l'ID
    public Optional<LamellatypeEntity> getById(Long id){
        return lamellaTypeJpaRepository.findById(id);
    }

    // POST : Ajouter un enregistrement
    public LamellatypeEntity add(LamellatypeEntity mushroomEntity) {
        return lamellaTypeJpaRepository.save(mushroomEntity);
    }

    // UPDATE : Mettre à jour un enregistrement
    public LamellatypeEntity put(LamellatypeEntity mushroomEntity){
        return lamellaTypeJpaRepository.save(mushroomEntity);
    }

    // PATCH : Mise à jour partiel d'un enregistrement
    public LamellatypeEntity patch(LamellatypeEntity lamellatypeEntity) {
        return lamellaTypeJpaRepository.save(lamellatypeEntity);
    }

    // delete : Supprimer un enregistrement
    public void delete(Long id) {
        lamellaTypeJpaRepository.deleteById(id);
    }

}
