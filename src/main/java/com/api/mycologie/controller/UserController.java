package com.api.mycologie.controller;

import com.api.mycologie.entity.UserEntity;
import com.api.mycologie.service.UserService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api")
public class UserController {

    //@Autowired
    //UserService userService;
    final UserService userService;

    // Afficher tous les utilisateurs
    @GetMapping("/users")
     public Iterable<UserEntity> GetAllUsers (){
        return userService.getAllUsers();
     }

    //Afficher un utilisateur via son ID
    @GetMapping("/user/{userId}")
    public Optional<UserEntity> getUserById(@PathVariable("userId") long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/user")
    public void addUser(@RequestBody UserEntity userEntity){
        userService.addUser(userEntity);
    }

    @PutMapping("/user/{userId}")
    public void updateUser(@PathVariable("userId") long userId, @RequestBody UserEntity userEntity){
        userService.updateUser(userId,userEntity);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }


}
