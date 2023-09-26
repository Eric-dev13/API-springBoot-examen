package com.api.mushroom.controller.testing;


import com.api.mushroom.service.utils.FileUploadService;
import com.api.mushroom.service.utils.UniqueSlugService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/upload")
public class FileUploadController {

    // Via l'annotation @RequiredArgsConstructor Lombok va générer un constructeur avec un paramètre pour chaque constante (final)
    private final FileUploadService fileUploadService;
    private final UniqueSlugService uniqueSlugService;


    // TELECHARGEMENT DE FICHIER: Cette méthode renvoi vers le client le fichier demandé.
    // Est demandé en premiere paramètre le nom du sous-dossier d'upload (rubrique) et en deuxième paramètre le nom du fichier recherché.
    // le serveur retourne la réponse avec les données de l'image et les en-têtes
    @GetMapping("/{pathName}/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String pathName, @PathVariable String imageName) throws IOException {
        return fileUploadService.getImage(pathName, imageName);
    }

    @PostMapping("/fileUpload")
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        /* ****** GENERER UN NOM UNIQUE POUR LE FICHIER ******* */

        // Obtient le nom de fichier original à partir de l'objet MultipartFile
        String originalFilename = file.getOriginalFilename();

        // Trouve l'indice du dernier point dans le nom de fichier (l'indice de l'extension)
        int lastDotIndex = originalFilename.lastIndexOf(".");

        // Initialise les variables pour le nom de fichier sans extension et l'extension
        String filenameWithoutExtension, fileExtension;

        // Vérifie si un point a été trouvé dans le nom de fichier
        if (lastDotIndex != -1) {
            // Extrait le nom de fichier sans extension en utilisant la méthode substring
            filenameWithoutExtension = originalFilename.substring(0, lastDotIndex);

            // Extrait l'extension du fichier en utilisant la méthode substring
            fileExtension = originalFilename.substring(lastDotIndex + 1);
        } else {
            // Si aucun point n'est trouvé, utilise simplement le nom de fichier original
            filenameWithoutExtension = originalFilename;

            // L'extension est initialisée comme une chaîne vide
            fileExtension = "";
        }

        // Génère un slug unique à partir du nom original du fichier en utilisant le service UniqueSlugService
        String slugifyOriginalFilename = uniqueSlugService.generateUniqueSlug( filenameWithoutExtension );


        /* ****** TELEVERSE LE FICHIER ******* */

        // Récupérer le répertoire public absolu
        Path publicDirectory = Paths.get(".", "public").toAbsolutePath();

        // Lire le contenu du fichier en bytes
        byte[] imageContent = file.getBytes();

        // Créer le chemin complet du fichier à enregistrer dans le répertoire public
        Path filepath = Paths.get(publicDirectory.toString(), slugifyOriginalFilename + '.' + fileExtension);

        try (OutputStream os = Files.newOutputStream(filepath)) {
            // Écrire le contenu du fichier dans le chemin spécifié
            os.write(imageContent);
        } catch (IOException e) {
            // En cas d'erreur lors de l'écriture du fichier, afficher la trace d'erreur
            e.printStackTrace();
        }
        // Le fichier a été enregistré avec succès, retourner une réponse OK
        return ResponseEntity.ok("Fichier téléchargé avec succès.");

    }


}
