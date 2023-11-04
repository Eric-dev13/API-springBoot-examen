package com.api.mushroom.controller.admin;

import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.service.MushroomService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;



@RestController
//@Validated // validator
@RequiredArgsConstructor
//@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("api/v1/admin/mushroom")
public class MushroomCrudController {

    // Via la constante (final) l'annotation @RequiredArgsConstructor Lombok va injecter le service dans le constructeur
    private final MushroomService mushroomService;

    // GET - FIND ALL - Récupère un tableau d'enregistrement trié par nom commun.
    @GetMapping
    public Iterable<MushroomEntity> getAll() {
        return mushroomService.getAll();
    }

    // GET - FIND BY ID
    @GetMapping("/{id}")
    public Optional<MushroomEntity> getById(@PathVariable("id") Long id) {
        return mushroomService.getById(id);
    }

    /**
     * @Valid : déclenche la validation des données
     * @param mushroomEntity
     * @return
     */
    @PostMapping("/validator")
    public ResponseEntity<?> addtest(@Valid @RequestBody MushroomEntity mushroomEntity) {
        try {
            MushroomEntity mushroom = mushroomService.add(mushroomEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(mushroom); // 201 Created en cas de succès
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout : " + e.getMessage()); // 500 Internal Server Error en cas d'erreur.
        }
    }

    // POST
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody MushroomEntity mushroomEntity) {
        try {
            MushroomEntity mushroom = mushroomService.add(mushroomEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(mushroom); // 201 Created en cas de succès
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout : " + e.getMessage()); // 500 Internal Server Error en cas d'erreur.
        }
    }

    // UPDATE : Mise à jour complète d'un enregistrement
    @PutMapping("/{id}")
    public MushroomEntity put(@PathVariable("id") Long id, @Valid @RequestBody MushroomEntity mushroomEntity) {
        return mushroomService.put(id, mushroomEntity);
    }

    // Inverse la valeur booléen du champ visibility
    @PutMapping("/publier/{id}")
    public ResponseEntity<Boolean> invertPublish(@PathVariable("id") Long id) {
        boolean isUpdated = mushroomService.invertPublish(id);
        if(isUpdated){
            return ResponseEntity.ok(true); // 200 en cas de succès
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found en cas d'erreur.
        }
    }

    // DELETE : Supprimer un enregistrement
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Long id){
        return mushroomService.delete(id);
    }

}
