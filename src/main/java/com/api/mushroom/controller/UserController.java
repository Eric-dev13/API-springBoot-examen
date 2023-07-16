package com.api.mushroom.controller;

import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.service.UserService;
import com.api.mushroom.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/user")
public class UserController {

    // Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final UserService userService;


    // GET : Afficher tous les utilisateurs
    @Secured("ROLE_USER")
    @GetMapping("/")
     public Iterable<UserEntity> allUsers (){
        // retourne la liste des utilisateurs
        return userService.getAll();
     }

     // GET : Afficher un utilisateur via son ID
    @GetMapping("/{id}")
    public Optional<UserEntity> getUserById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @PostMapping("/")
    public UserEntity addUser(@RequestBody UserEntity userEntity) {
        return userService.add(userEntity);
    }

    @PutMapping("/{id}")
    public void editUser(@PathVariable("id") final String id, @RequestBody final UserEntity userEntity) {
        userService.edit(userEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
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
