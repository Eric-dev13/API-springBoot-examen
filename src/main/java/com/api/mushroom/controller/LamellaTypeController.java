package com.api.mushroom.controller;

import com.api.mushroom.entity.LamellatypeEntity;
import com.api.mushroom.service.LamellaTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/lamellaType")
public class LamellaTypeController {
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

}
