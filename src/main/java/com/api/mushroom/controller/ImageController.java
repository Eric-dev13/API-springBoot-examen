package com.api.mushroom.controller;


import com.api.mushroom.service.utils.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/v1/file/upload")
public class ImageController {

    // Via l'annotation @RequiredArgsConstructor Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final ImageService imageService;

    /* Donc on n'a pas besoin d'injecter le service de cette autre manière
    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
     */

    @GetMapping("/{pathName}/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String pathName, @PathVariable String imageName) throws IOException {
        return imageService.getImage(pathName, imageName);
    }

}
