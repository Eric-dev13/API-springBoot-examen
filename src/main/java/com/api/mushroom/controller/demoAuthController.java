package com.api.mushroom.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class demoAuthController {

    @GetMapping(value = "/")
    public ResponseEntity<String> pong()
    {
        //logger.info("Démarrage des services OK .....");
        return new ResponseEntity<String>("Réponse du serveur: "+ HttpStatus.OK.name(), HttpStatus.OK);
    }
}
