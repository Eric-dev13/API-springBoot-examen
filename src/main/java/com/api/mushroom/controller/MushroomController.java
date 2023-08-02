package com.api.mushroom.controller;


import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.service.MushroomService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/v1/mushroom")
public class MushroomController {

    // Via l'annotation @RequiredArgsConstructor Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final MushroomService mushroomService;


    // GET - Retourne un tableau d'objets - liste de tous les enregisterments validé par l'administrateur pour la publication.
    @GetMapping(name = "/")
    public Iterable<MushroomEntity> findAllByVisibility() {
        return mushroomService.findAllByVisibility(true);
    }


    // GET - Retourne un tableau de tableau de 3 propriétés.
    @GetMapping("/field-selected")
    public List findAllByVisibilityWithTitleImageEdibility() {
       return mushroomService.findAllByVisibilityWithTitleImageEdibility();
    }


    // GET - Afficher un utilisateur via son ID
    @GetMapping("/{id}")
    public Optional<MushroomEntity> getById(@PathVariable("id") Long id) {
        return mushroomService.getById(id);
    }

    @GetMapping("/test/{slug}")
    public Optional<MushroomEntity> findBySlug(@PathVariable("slug") String slug) {
        return mushroomService.findBySlug(slug);
    }
}
