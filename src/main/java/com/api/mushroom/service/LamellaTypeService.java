package com.api.mushroom.service;

import com.api.mushroom.entity.LamellatypeEntity;
import com.api.mushroom.repository.LamellaTypeJpaRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@RequiredArgsConstructor
@Data
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
    public LamellatypeEntity add(@RequestBody LamellatypeEntity mushroomEntity) {
        return lamellaTypeJpaRepository.save(mushroomEntity);
    }

    // UPDATE : Mettre à jour un enregistrement
    public LamellatypeEntity edit(@RequestBody LamellatypeEntity mushroomEntity){
        return lamellaTypeJpaRepository.save(mushroomEntity);
    }

    // delete : Supprimer un enregistrement
    public void delete(Long id) {
        lamellaTypeJpaRepository.deleteById(id);
    }
}
