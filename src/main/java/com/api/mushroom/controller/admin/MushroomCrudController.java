package com.api.mushroom.controller.admin;

import com.api.mushroom.entity.EdibilityEntity;
import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.service.MushroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/v1/admin/mushroom")
public class MushroomCrudController {

    // Via la constante (final) l'annotation @RequiredArgsConstructor Lombok va injecter le service dans le constructeur
    private final MushroomService mushroomService;

    // GET - FIND ALL - Récupère un tableau d'enregistrement trié par nom commun.
    @GetMapping(name = "/")
    public Iterable<MushroomEntity> getAll() {
        return mushroomService.getAll();
    }

/*    @GetMapping("/")
    public ResponseEntity<Iterable<MushroomEntity>> getAll() {
        Iterable<MushroomEntity> mushrooms = mushroomService.getAll();
        return ResponseEntity.ok(mushrooms);
    }*/

    // GET - FIND BY ID - Afficher un utilisateur via son ID
    @GetMapping("/{id}")
    public Optional<MushroomEntity> getById(@PathVariable("id") Long id) {
        return mushroomService.getById(id);
    }

    // POST : Ajouter un enregistrement
    @PostMapping("/")
    public MushroomEntity add(@RequestBody MushroomEntity mushroomEntity) {
        return mushroomService.add(mushroomEntity);
    }

    @PostMapping("/add")
    public MushroomEntity addNewMushroomWithFileUpolad(@RequestParam("file") MultipartFile file)
    {
        // upload du fichier
/*        String newFilename = fileUploadService.fileUpload(file, "edibility/");
        EdibilityEntity edibilityEntity = new EdibilityEntity();
        edibilityEntity.setName(name);
        edibilityEntity.setFilename(newFilename);
        edibilityJpaRepository.save(edibilityEntity);*/
        return mushroomService.addNewMushroomWithFileUpolad(file);
    }


    // UPDATE : Mise à jour complète d'un enregistrement
    @PutMapping("/{id}")
    public MushroomEntity put(@PathVariable("id") Long id, @RequestBody final MushroomEntity mushroomEntity) {
        return mushroomService.put(mushroomEntity);
    }

    // PATCH : Mise à jour partiel d'un enregistrement
    @PatchMapping("/{id}")
    public MushroomEntity patch(@PathVariable("id") Long id, @RequestBody final MushroomEntity mushroomEntity) {
        return mushroomService.patch(mushroomEntity);
    }

    // DELETE : Supprimer un enregistrement
    @DeleteMapping("/{id}")
    public void deleter(@PathVariable("id") Long id){
        mushroomService.delete(id);
    }

    // Inverse la valeur booléen du champ visibility
    @PatchMapping("/publier/{id}")
    public void invertPublish(@PathVariable("id") Long id) {
        mushroomService.invertPublish(id);
    }


}
