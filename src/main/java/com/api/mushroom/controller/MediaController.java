package com.api.mushroom.controller;


import com.api.mushroom.entity.MediaEntity;
import com.api.mushroom.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/media")
public class MediaController {
    // Via l'annotation @RequiredArgsConstructor Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final MediaService mediaService;

    // GET - Récupère un tableau d'enregistrement
    @GetMapping(name = "/")
    public Iterable<MediaEntity> getAll() {
        return mediaService.getAll();
    }

    // GET : Afficher un utilisateur via son ID
    @GetMapping("/{id}")
    public Optional<MediaEntity> getById(@PathVariable("id") Long id) {
        return mediaService.getById(id);
    }

}
