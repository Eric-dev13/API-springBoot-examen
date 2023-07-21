package com.api.mushroom.controller;

import com.api.mushroom.entity.LamellatypeEntity;
import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.service.LamellaTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/lamellaType")
public class LamellaTypeController {
    // Via l'annotation @RequiredArgsConstructor Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    final LamellaTypeService lamellaTypeService;

    // GET - Récupère un tableau d'enregistrement
    @GetMapping(name = "/")
    public Iterable<LamellatypeEntity> getAll() {
        return lamellaTypeService.getAll();
    }

    // GET : Afficher un utilisateur via son ID
    @GetMapping("/{id}")
    public Optional<LamellatypeEntity> getById(@PathVariable("id") Long id) {
        return lamellaTypeService.getById(id);
    }

    // POST : Ajouter un enregistrement
    @PostMapping("/")
    public LamellatypeEntity add(@RequestBody LamellatypeEntity lamellatypeEntity) {
        return lamellaTypeService.add(lamellatypeEntity);
    }

    // UPDATE : Mettre à jour un enregistrement
    @PutMapping("/{id}")
    public void edit(@PathVariable("id") final String id, @RequestBody final LamellatypeEntity lamellatypeEntity) {
        lamellaTypeService.edit(lamellatypeEntity);
    }

    // DELETE : Supprimer un enregistrement
    @DeleteMapping("/{id}")
    public void deleter(@PathVariable("id") Long id){
        lamellaTypeService.delete(id);
    }
}
