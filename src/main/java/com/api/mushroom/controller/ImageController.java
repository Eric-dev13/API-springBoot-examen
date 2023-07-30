package com.api.mushroom.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/v1/file/upload")
public class ImageController {
    @GetMapping("/{pathName}/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String pathName, @PathVariable String imageName) throws IOException {

        // Construire le chemin d'accès complet vers l'image demandée
        String fullImagePath = "upload/" + pathName + "/" + imageName;

        // Obtenir le chemin d'accès complet du fichier image depuis le répertoire src/main/resources
        ClassPathResource classPathResource = new ClassPathResource(fullImagePath);
        Path imageFilePath = classPathResource.getFile().toPath();

        // Vérifier si le fichier existe
        if (Files.exists(imageFilePath)) {
            // Lire le contenu du fichier image
            byte[] imageBytes = Files.readAllBytes(imageFilePath);

            // Définir le type de contenu (Content-Type) de la réponse
            String contentType = determineContentType(imageFilePath);

            // Construire les en-têtes de la réponse avec le type de contenu
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(contentType));

            // Retourner la réponse avec les données de l'image et les en-têtes
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            // Si le fichier image n'existe pas, retourner une réponse 404 (Non trouvé)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Méthode pour déterminer le type de contenu (Content-Type) de l'image
    private String determineContentType(Path filePath) throws IOException {
        String contentType;
        try {
            contentType = Files.probeContentType(filePath);
        } catch (IOException e) {
            // En cas d'erreur lors de la détermination du type de contenu, on utilise un type par défaut
            contentType = "application/octet-stream";
        }
        return contentType;
    }
}
