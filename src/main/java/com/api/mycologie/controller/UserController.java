package com.api.mycologie.controller;

import com.api.mycologie.entity.UserEntity;
import com.api.mycologie.service.UserService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api")
public class UserController {
    final UserService userService;

    @GetMapping("/users")
     public Iterable<UserEntity> GetAllUsers (){
         return userService.getAllUsers();
     }

}
