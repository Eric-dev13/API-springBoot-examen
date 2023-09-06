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
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
//@Data
@Service
public class MediaService {


    private final MediaJpaRepository mediaJpaRepository;
    private final FileUploadService fileUploadService;
    private final MushroomJpaRepository mushroomJpaRepository;

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
    public List<MediaEntity> addMediasWithFileUpolad(Long id, List<String> mediasNames, List<MultipartFile> mediasFiles) throws IOException {
        // Obtenir l'enregistrement champignon correspondant à l'ID fourni
        MushroomEntity mushroomEntity  = mushroomJpaRepository.findById(id).orElseThrow();

        // Initialise une liste pour stocker les nouvelles entités MediaEntity créées
        List <MediaEntity> mediaEntities = new ArrayList<>();

        // Parcourir chaque média à ajouter
        for (int i = 0; i < mediasNames.size(); i++) {
            // Récupérer le nom du média et le fichier correspondant
            String mediaName = mediasNames.get(i);
            MultipartFile mediaFile = mediasFiles.get(i);

            // Télécharger le fichier de média et obtenir le nouveau nom de fichier
            String newFilename = fileUploadService.fileUpload(mediaFile, "mushrooms/");

            // Créer une nouvelle entité MediaEntity
            MediaEntity mediaEntity = new MediaEntity();
            mediaEntity.setName(mediaName);
            mediaEntity.setFilename(newFilename);
            mediaEntity.setMushroomEntity(mushroomEntity);

            // Enregistrer la nouvelle entité MediaEntity dans la base de données
            mediaJpaRepository.save(mediaEntity);

            // Ajouter la nouvelle entité MediaEntity à la liste des entités créées
            mediaEntities.add(mediaEntity);
        }
        // Retourner la liste des entités MediaEntity ajoutées dans la table des médias
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
