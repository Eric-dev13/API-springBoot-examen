package com.api.mushroom.controller;

import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.service.UserService;
import jakarta.persistence.NamedQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/user")
@NamedQueries({ })
public class UserController {

    // Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final UserService userService;


    // GET - Récupère un tableau d'enregistrement
    @Secured("ROLE_USER")
    @GetMapping("/")
     public Iterable<UserEntity> getAll(){
        // retourne la liste des utilisateurs
        return userService.getAll();
     }

    // GET - Récupère un enregistrement par l'ID
    @GetMapping("/{id}")
    public Optional<UserEntity> getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    // POST : Ajouter un enregistrement
    @PostMapping("/")
    public UserEntity add(@RequestBody UserEntity userEntity) {
        return userService.add(userEntity);
    }

    // UPDATE : Mettre à jour un enregistrement
    @PutMapping("/{id}")
    public UserEntity edit(@PathVariable("id") final String id, @RequestBody final UserEntity userEntity) {
        return userService.edit(userEntity);
    }

    // DELETE : Supprimer un enregistrement
    @DeleteMapping("/{id}")
    public void deleter(@PathVariable("id") Long id){
        userService.delete(id);
    }


    /*

    // POST : Ajouter un compte utilisateur
    @PostMapping("/")
    public void addUser(@RequestBody UserDTO userDto){
        userService.add(userDto);
    }

    // GET : Afficher un utilisateur via son ID
    @GetMapping("/{userId}")
    public UserDTO userById(@PathVariable("userId") long userId) {
        return userService.getById(userId);
    }

    @PutMapping("/{userId}")
    public void edit(@PathVariable("userId") long userId, @RequestBody UserEntity userEntity){
        userService.adduser(userId,userEntity);
    }

    // DELETE : supprimer un compte utilisateur
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }


     */


}
