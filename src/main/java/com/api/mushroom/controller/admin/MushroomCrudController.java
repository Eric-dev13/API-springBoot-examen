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

    // GET - Récupère un tableau d'enregistrement
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

    // UPDATE : Mettre à jour un enregistrement
    @PutMapping("/{id}")
    public MushroomEntity edit(@PathVariable("id") final String id, @RequestBody final MushroomEntity mushroomEntity) {
        return mushroomService.edit(mushroomEntity);
    }

    // DELETE : Supprimer un enregistrement
    @DeleteMapping("/{id}")
    public void deleter(@PathVariable("id") Long id){
        mushroomService.delete(id);
    }
}
