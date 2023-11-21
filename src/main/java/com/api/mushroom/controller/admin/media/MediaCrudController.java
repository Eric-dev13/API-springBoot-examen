package com.api.mushroom.controller.admin.media;


import com.api.mushroom.entity.MediaEntity;
import com.api.mushroom.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
//@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("api/v1/admin/media")
public class MediaCrudController {
    // Via l'annotation @RequiredArgsConstructor Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final MediaService mediaService;


    // GET - Récupère un tableau d'enregistrement
    @GetMapping
    public Iterable<MediaEntity> getAll() {
        return mediaService.getAll();
    }

    // GET : Afficher un utilisateur via son ID
    @GetMapping("/{id}")
    public Optional<MediaEntity> getById(@PathVariable("id") Long id) {
        return mediaService.getById(id);
    }

    // POST : Ajouter un enregistrement
//    @PostMapping
//    public MediaEntity add(@RequestBody MediaEntity mediaEntity) {
//        return mediaService.add(mediaEntity);
//    }

    @PostMapping("/{id}")  // ID de l'enregistrement champignon correspondant
    public List<MediaEntity> addMediasWithFileUpoladAndName(@PathVariable("id") Long id,
                                                     @RequestParam("mediasNames") List<String> mediasNames,
                                                     @RequestPart("mediasFiles") List<MultipartFile> mediasFiles) throws IOException {
        return mediaService.addMediasWithFileUpoladAndName(id, mediasNames, mediasFiles);
    }

    @PostMapping("/upload/{id}")  // ID de l'enregistrement champignon correspondant
    public ResponseEntity<?> addMediasWithFileUpolad(@PathVariable("id") Long id,
                                                                      @RequestPart("mediasFiles") Optional<List<MultipartFile>> mediasFiles) throws IOException {
        if(mediasFiles.isPresent()) {
            List<MediaEntity> mediaEntities = mediaService.addMediasWithFileUpolad(id, mediasFiles.get());
            if (mediaEntities != null) {
                return new ResponseEntity(mediaEntities, HttpStatus.CREATED);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La requête est vide, aucune donnée fournie.");
            }
        }
        return null;
    }

    // UPDATE : Mettre à jour un enregistrement
    @PutMapping("/{id}")
    public MediaEntity edit(@PathVariable("id") Long id, @RequestBody final MediaEntity mediaEntity) {
        return mediaService.edit(mediaEntity);
    }

    // DELETE : Supprimer un enregistrement
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Long id){
        return mediaService.delete(id);
    }


    @PostMapping("/new")
    public boolean fileUpload(MultipartFile mediaFile) throws IOException {
        /* ***************************************************** */
        /*                   TELEVERSE LE FICHIER                */
        /* ***************************************************** */
        // Obtient le nom de fichier original à partir de l'objet MultipartFile
        String originalFilename = mediaFile.getOriginalFilename();

        // Récupérer le répertoire public absolu
        Path publicDirectory = Paths.get(".", "public/upload").toAbsolutePath();

        // Créer le chemin complet du fichier à enregistrer dans le dossier upload du répertoire public
        Path filepath = Paths.get(publicDirectory.toString(), originalFilename);

        // Lire le contenu du fichier en bytes
        byte[] imageContent = mediaFile.getBytes();

        try (OutputStream os = Files.newOutputStream(filepath)) {
            // Écrire le contenu du fichier dans le chemin spécifié
            os.write(imageContent);
            return true;
        } catch (IOException e) {
            // En cas d'erreur lors de l'écriture du fichier, afficher la trace d'erreur
            e.printStackTrace();
            return false;
        }
    }

}
