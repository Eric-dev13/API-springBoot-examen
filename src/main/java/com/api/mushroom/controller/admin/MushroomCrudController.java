package com.api.mushroom.controller.admin;

import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.service.MushroomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
//@CrossOrigin
@RequestMapping("api/v1/admin/mushroom")
public class MushroomCrudController {

    // Via la constante (final) l'annotation @RequiredArgsConstructor Lombok va injecter le service dans le constructeur
    private final MushroomService mushroomService;

    // GET - FIND ALL - Récupère un tableau d'enregistrement trié par nom commun.
    // @Secured("ADMIN")
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
    public MushroomEntity add(@Valid @RequestBody MushroomEntity mushroomEntity, BindingResult result) {
        if(result.hasErrors())
        {
            System.out.println("error");
        }
        return mushroomService.add(mushroomEntity);
    }


    // UPDATE : Mise à jour complète d'un enregistrement
    @PutMapping("/{id}")
    public MushroomEntity put(@PathVariable("id") Long id, @RequestBody final MushroomEntity mushroomEntity) {
        return mushroomService.put(id, mushroomEntity);
    }

    // PATCH : Mise à jour partiel d'un enregistrement
    @PatchMapping("/{id}")
    public MushroomEntity patch(@PathVariable("id") Long id, @RequestBody final MushroomEntity mushroomEntity) {
        return mushroomService.patch(id, mushroomEntity);
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
