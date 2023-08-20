package com.api.mushroom.controller.admin;

import com.api.mushroom.entity.EdibilityEntity;
import com.api.mushroom.entity.LamellatypeEntity;
import com.api.mushroom.service.LamellaTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/v1/admin/lamellaType")
public class LamellaTypeCrudController {
    // Via l'annotation @RequiredArgsConstructor Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final LamellaTypeService lamellaTypeService;

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
    public LamellatypeEntity put(@PathVariable("id") Long id, @RequestBody final LamellatypeEntity lamellatypeEntity) {
        return lamellaTypeService.put(lamellatypeEntity);
    }

    // PATCH : Mise à jour partiel d'un enregistrement
    @PatchMapping("/{id}")
    public LamellatypeEntity patch(@PathVariable("id")  Long id, @RequestBody final LamellatypeEntity lamellatypeEntity) {
        return lamellaTypeService.patch(lamellatypeEntity);
    }

    // DELETE : Supprimer un enregistrement
    @DeleteMapping("/{id}")
    public void deleter(@PathVariable("id") Long id){
        lamellaTypeService.delete(id);
    }
}
