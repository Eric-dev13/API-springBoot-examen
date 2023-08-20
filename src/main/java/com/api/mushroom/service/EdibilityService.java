package com.api.mushroom.service;

import com.api.mushroom.entity.EdibilityEntity;
import com.api.mushroom.entity.LamellatypeEntity;
import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.repository.EdibilityJpaRepository;
import com.api.mushroom.repository.LamellaTypeJpaRepository;
import com.api.mushroom.service.utils.FileUploadService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

//@Data
@RequiredArgsConstructor
@Service
public class EdibilityService {

    private final EdibilityJpaRepository edibilityJpaRepository;
    private final FileUploadService fileUploadService;


    // GET - Récupère un tableau d'enregistrement
    public Iterable<EdibilityEntity> getAll() {
        return edibilityJpaRepository.findAll();
    }

    // GET - Récupère un enregistrement par l'ID
    public Optional<EdibilityEntity> getById(Long id){
        return edibilityJpaRepository.findById(id);
    }

    // POST : Ajouter un nouvel enregistrement avec upload du fichier
    public ResponseEntity<?> addEdibilityWithFile(MultipartFile file, String name) throws IOException {
        // upload du fichier
        String newFilename = fileUploadService.fileUpload(file, "edibility/");
        EdibilityEntity edibilityEntity = new EdibilityEntity();
        edibilityEntity.setName(name);
        edibilityEntity.setPath(newFilename);
        edibilityJpaRepository.save(edibilityEntity);
        return ResponseEntity.ok("{\"message\": \"Données traitées avec succès!\"}");
    }

    // UPDATE : Mettre à jour un enregistrement
    public EdibilityEntity put(EdibilityEntity edibilityEntity){
        return edibilityJpaRepository.save(edibilityEntity);
    }

    // PATCH : Mise à jour partiel d'un enregistrement
    public EdibilityEntity patch(Long id, MultipartFile file, String name) throws IOException {
        //  Si aucune entité EdibilityEntity n'est trouvée avec l'ID spécifié nne exception est lever si la valeur n'est pas présente dans l'optionnel (Optional),
        // une NoSuchElementException sera levée avec le message d'erreur spécifié.
        EdibilityEntity edibilityEntity = edibilityJpaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Aucune entité EdibilityEntity trouvée avec l'ID : " + id)
        );

        // Appliquer les mises à jour partielles à l'entité existante
        if (name != null) {
            edibilityEntity.setName(name);
        }

        // Mettre à jour le fichier si fourni
        if (file != null) {
            // Copie le fichier image sur le serveur et retourne le nouveau nom du fichier.
            // Ajoute le nom nom dans la db
            String newFilename = fileUploadService.fileUpload(file, "edibility/");
            edibilityEntity.setPath(newFilename);
        }

        return edibilityJpaRepository.save(edibilityEntity);
    }


    // delete : Supprimer un enregistrement
    public void delete(Long id) {
        edibilityJpaRepository.deleteById(id);
    }


}
