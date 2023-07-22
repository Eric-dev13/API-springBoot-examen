package com.api.mushroom.controller;

import com.api.mushroom.entity.EdibilityEntity;
import com.api.mushroom.service.EdibilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/edibility")
public class EdibiltyController {

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

    // POST : Ajouter un enregistrement
    @PostMapping("/")
    public EdibilityEntity add(@RequestBody EdibilityEntity edibilityEntity) {
        return edibilityService.add(edibilityEntity);
    }

    // UPDATE : Mettre à jour un enregistrement
    @PutMapping("/{id}")
    public EdibilityEntity edit(@PathVariable("id") final String id, @RequestBody final EdibilityEntity edibilityEntity) {
        return edibilityService.edit(edibilityEntity);
    }

    // DELETE : Supprimer un enregistrement
    @DeleteMapping("/{id}")
    public void deleter(@PathVariable("id") Long id){
        edibilityService.delete(id);
    }
}
