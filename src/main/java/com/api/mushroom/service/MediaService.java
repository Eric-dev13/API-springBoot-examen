package com.api.mushroom.service;

import com.api.mushroom.entity.MediaEntity;
import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.repository.MediaJpaRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@RequiredArgsConstructor
@Data
@Service
public class MediaService {

    //private final MushroomRepository mushroomRepository;
    private final MediaJpaRepository mediaJpaRepository;

    // GET - Récupère un tableau d'enregistrement
    public Iterable<MediaEntity> getAll() {
        return mediaJpaRepository.findAll();
    }

    // GET - Récupère un enregistrement par l'ID
    public Optional<MediaEntity> getById(Long id){
        return mediaJpaRepository.findById(id);
    }

    // POST : Ajouter un enregistrement
    public MediaEntity add(@RequestBody MediaEntity mediaEntity) {
        return mediaJpaRepository.save(mediaEntity);
    }

    // UPDATE : Mettre à jour un enregistrement
    public MediaEntity edit(@RequestBody MediaEntity mediaEntity){
        return mediaJpaRepository.save(mediaEntity);
    }

    // delete : Supprimer un enregistrement
    public void delete(Long id) {
        mediaJpaRepository.deleteById(id);
    }
}
