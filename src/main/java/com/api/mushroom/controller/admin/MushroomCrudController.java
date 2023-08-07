package com.api.mushroom.controller.admin;

import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.service.MushroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/v1/admin/mushroom")
public class MushroomCrudController {
    // Via l'annotation @RequiredArgsConstructor Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final MushroomService mushroomService;

    // GET - Récupère un tableau d'enregistrement trié.
    @GetMapping(name = "/")
    public Iterable<MushroomEntity> getAll() {
        return mushroomService.getAll();
    }

    // GET : Afficher un utilisateur via son ID
    @GetMapping("/{id}")
    public Optional<MushroomEntity> getById(@PathVariable("id") Long id) {
        return mushroomService.getById(id);
    }

    // POST : Ajouter un enregistrement
    @PostMapping("/")
    public MushroomEntity add(@RequestBody MushroomEntity mushroomEntity) {
        return mushroomService.add(mushroomEntity);
    }

    // UPDATE : Mise à jour complète d'un enregistrement
    @PutMapping("/{id}")
    public MushroomEntity put(@PathVariable("id") final String id, @RequestBody final MushroomEntity mushroomEntity) {
        return mushroomService.put(mushroomEntity);
    }

    // PATCH : Mise à jour  partiel d'un enregistrement
    @PatchMapping("/{id}")
    public MushroomEntity patch(@PathVariable("id") final String id, @RequestBody final MushroomEntity mushroomEntity) {
        return mushroomService.patch(mushroomEntity);
    }

    // DELETE : Supprimer un enregistrement
    @DeleteMapping("/{id}")
    public void deleter(@PathVariable("id") Long id){
        mushroomService.delete(id);
    }

    @PatchMapping("/publier/{id}")
    public void invertPublish(@PathVariable("id") Long id) {
        mushroomService.invertPublish(id);
    }

    @GetMapping("/test/{titre}")
    public  Iterable<MushroomEntity> getSearch(@PathVariable("titre") String titre) {
        return mushroomService.getSearch(titre);
    }
}
