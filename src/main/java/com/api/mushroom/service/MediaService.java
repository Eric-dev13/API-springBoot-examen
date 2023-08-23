package com.api.mushroom.service;

import com.api.mushroom.entity.MediaEntity;
import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.repository.MediaJpaRepository;
import com.api.mushroom.repository.MushroomJpaRepository;
import com.api.mushroom.service.utils.FileUploadService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
//@Data
@Service
public class MediaService {


    private final MediaJpaRepository mediaJpaRepository;
    private final FileUploadService fileUploadService;
    private final MushroomService mushroomService;

    // GET - Récupère un tableau d'enregistrement
    public Iterable<MediaEntity> getAll() {
        return mediaJpaRepository.findAll();
    }

    // GET - Récupère un enregistrement par l'ID
    public Optional<MediaEntity> getById(Long id){
        return mediaJpaRepository.findById(id);
    }

    // POST : Ajouter un enregistrement
    public MediaEntity add(MediaEntity mediaEntity) {
        return mediaJpaRepository.save(mediaEntity);
    }

    // POST : Ajouter une collection de médias nom + fileUpload
    @PostMapping("/add")
    public List<MediaEntity> addMediasWithFileUpolad(Long id, List<String> mediasNames, List<MultipartFile> mediasFiles) throws IOException {
        // obtenir l'enregistrement mushroom
        MushroomEntity mushroomEntity = mushroomService.getById(id).orElseThrow();
        // Initialise une liste pour stocker tout les nouveaux enregistrements de médias
        List <MediaEntity> mediaEntities = new ArrayList<>();

        // upload des fichiers
        for (int i = 0; i < mediasNames.size(); i++) {
            String mediaName = mediasNames.get(i);
            MultipartFile mediaFile = mediasFiles.get(i);

            String newFilename = fileUploadService.fileUpload(mediaFile, "mushrooms/");
            MediaEntity mediaEntity = new MediaEntity();
            mediaEntity.setName(mediaName);
            mediaEntity.setFilename(newFilename);
            mediaEntity.setMushroomEntity(mushroomEntity);
            mediaJpaRepository.save(mediaEntity);
            mediaEntities.add(mediaEntity);
        }
        // retourne la liste des médias ajoutés dans la table médias.
        return mediaEntities;
    }

    // UPDATE : Mettre à jour un enregistrement
    public MediaEntity edit(MediaEntity mediaEntity){
        return mediaJpaRepository.save(mediaEntity);
    }

    // delete : Supprimer un enregistrement
    public void delete(Long id) {
        mediaJpaRepository.deleteById(id);
    }
}
