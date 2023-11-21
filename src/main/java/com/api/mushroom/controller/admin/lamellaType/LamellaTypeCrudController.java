package com.api.mushroom.controller.admin.lamellaType;

import com.api.mushroom.entity.EdibilityEntity;
import com.api.mushroom.entity.LamellatypeEntity;
import com.api.mushroom.service.LamellaTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
//@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("api/v1/admin/lamellaType")
public class LamellaTypeCrudController {
    // Via l'annotation @RequiredArgsConstructor Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final LamellaTypeService lamellaTypeService;

    // GET - Récupère un tableau d'enregistrement
    @GetMapping
    public Iterable<LamellatypeEntity> getAll() {
        return lamellaTypeService.getAll();
    }

    // GET : Afficher un utilisateur via son ID
    @GetMapping("/{id}")
    public Optional<LamellatypeEntity> getById(@PathVariable("id") Long id) {
        return lamellaTypeService.getById(id);
    }

    // POST : Ajouter un enregistrement
    @PostMapping
    public LamellatypeEntity add(@RequestBody LamellatypeEntity lamellatypeEntity) {
        return lamellaTypeService.add(lamellatypeEntity);
    }

    // UPDATE : Mettre à jour un enregistrement
    @PutMapping("/{id}")
    public LamellatypeEntity put(@PathVariable("id") Long id, @RequestBody final LamellatypeEntity lamellatypeEntity) {
        return lamellaTypeService.put(lamellatypeEntity);
    }


    // DELETE : Supprimer un enregistrement
    @DeleteMapping("/{id}")
    public boolean deleter(@PathVariable("id") Long id){
        return lamellaTypeService.delete(id);
    }
}
