package com.api.mushroom.controller.admin;

import com.api.mushroom.entity.EdibilityEntity;
import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.repository.EdibilityJpaRepository;
import com.api.mushroom.service.EdibilityService;
import com.api.mushroom.service.utils.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
//@CrossOrigin
@RequestMapping("api/v1/admin/edibility")
public class EdibiltyCrudController {

    // Via l'annotation @RequiredArgsConstructor Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final EdibilityService edibilityService;


    // GET - Récupère un tableau d'enregistrement
    @GetMapping(name = "/")
    public Iterable<EdibilityEntity> getAll() {
        return edibilityService.getAll();
    }

    // GET : Afficher un utilisateur via son ID
    @GetMapping("/{id}")
    public Optional<EdibilityEntity> getById(@PathVariable("id") Long id) {
        return edibilityService.getById(id);
    }


    // TODO : gerer la gestion de l'upload Form-data et des données textuelles au format JSON.
    @PostMapping(value = "/")
    public ResponseEntity<?> addEdibilityWithFile(@RequestParam("file") MultipartFile file,
                                                  @RequestParam("name") String name) throws IOException {
       if (file != null) {
           return edibilityService.addEdibilityWithFile(file,name);
       }
       return null;
    }

    // PATCH : Mise à jour partiel d'un enregistrement
    @PatchMapping("/{id}")
    public EdibilityEntity patch(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file,
                                  @RequestParam("name") String name) throws IOException {
            return edibilityService.patch(id, file, name);
    }

    // UPDATE : Mettre à jour un enregistrement
    @PutMapping("/{id}")
    public EdibilityEntity put(@PathVariable("id") Long id, @RequestBody EdibilityEntity edibilityEntity) {
        return edibilityService.put(edibilityEntity);
    }

    // DELETE : Supprimer un enregistrement
    @DeleteMapping("/{id}")
    public void deleter(@PathVariable("id") Long id){
         // TODO : gerer la suppression des fichiers
        edibilityService.delete(id);
    }

    /*
    // POST : Ajouter un enregistrement
    @PostMapping("/")
    public EdibilityEntity add(@RequestBody EdibilityEntity edibilityEntity) throws IOException {
        return edibilityService.add(edibilityEntity);
    }
    */
}
