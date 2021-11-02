package com.api.mycologie.controller;

import com.api.mycologie.entity.UserEntity;
import com.api.mycologie.service.UserService;
import com.api.mycologie.service.dto.UserDTO;
import com.api.mycologie.service.mapperdao.UserDAO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api")
public class UserController {

    //@Autowired
    //UserService userService;
    private final UserService userService;


    // Afficher tous les utilisateurs
    @GetMapping("/users")
     public Iterable<UserEntity> GetAllUsers (){
        return userService.getAllUsers();
     }

    // GET : Afficher un utilisateur via son ID
    @GetMapping("/user/{userId}")
    public UserDTO userById(@PathVariable("userId") long userId) {
        return userService.getUserById(userId);
    }

    // POST : Ajouter un compte utilisateur
    @PostMapping("/user")
    public void addUser(@RequestBody UserDTO userDTO){
        userService.addUser(userDTO);
    }

    // UPDATE : Mettre à jour un compte utilisateur
    @PutMapping("/user/{userId}")
    public void updateUser(@PathVariable("userId") long userId, @RequestBody UserEntity userEntity){
        userService.updateUser(userId,userEntity);
    }

    // DELETE : supprimer un compte utilisateur
    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }


}
