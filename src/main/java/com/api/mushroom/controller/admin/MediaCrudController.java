package com.api.mushroom.controller.admin;


import com.api.mushroom.entity.MediaEntity;
import com.api.mushroom.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/v1/admin/media")
public class MediaCrudController {
    // Via l'annotation @RequiredArgsConstructor Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final MediaService mediaService;

    // GET - Récupère un tableau d'enregistrement
    @GetMapping(name = "/")
    public Iterable<MediaEntity> getAll() {
        return mediaService.getAll();
    }

    // GET : Afficher un utilisateur via son ID
    @GetMapping("/{id}")
    public Optional<MediaEntity> getById(@PathVariable("id") Long id) {
        return mediaService.getById(id);
    }

    // POST : Ajouter un enregistrement
    @PostMapping("/")
    public MediaEntity add(@RequestBody MediaEntity mediaEntity) {
        return mediaService.add(mediaEntity);
    }

    @PostMapping("/{id}")
    public List<MediaEntity> addMediasWithFileUpolad(@PathVariable("id") Long id,
                                                     @RequestParam("mediasNames") List<String> mediasNames,
                                                     @RequestPart("mediasFiles") List<MultipartFile> mediasFiles) throws IOException {
        return mediaService.addMediasWithFileUpolad(id, mediasNames, mediasFiles);
    }

    // UPDATE : Mettre à jour un enregistrement
    @PutMapping("/{id}")
    public MediaEntity edit(@PathVariable("id") Long id, @RequestBody final MediaEntity mediaEntity) {
        return mediaService.edit(mediaEntity);
    }

    // DELETE : Supprimer un enregistrement
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        mediaService.delete(id);
    }

}
