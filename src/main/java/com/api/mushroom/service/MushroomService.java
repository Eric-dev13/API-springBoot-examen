package com.api.mushroom.service;

import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.repository.MushroomJpaRepository;
import com.api.mushroom.repository.MushroomRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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


}
