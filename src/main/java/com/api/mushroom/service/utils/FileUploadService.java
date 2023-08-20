package com.api.mushroom.service.utils;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
public class FileUploadService {

    private final UniqueSlugService uniqueSlugService;


    public ResponseEntity<byte[]> getImage(String pathName, String imageName) throws IOException {
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

    public String fileUpload(MultipartFile file, String parentPath) throws IOException {
        /* ***************************************************** */
        /*         GENERER UN NOM UNIQUE POUR LE FICHIER         */
        /* ***************************************************** */
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

        /* ***************************************************** */
        /*                   TELEVERSE LE FICHIER                */
        /* ***************************************************** */
        // Récupérer le répertoire public absolu
        Path publicDirectory = Paths.get(".", "public/upload").toAbsolutePath();

        // Créer le chemin complet du sous-dossier à l'intérieur du répertoire public
        Path subDirectory = Paths.get(publicDirectory.toString(), parentPath);

        // Si le sous-dossier n'existe pas, le créer
        if (!Files.exists(subDirectory)) {
            Files.createDirectories(subDirectory);
        }

        // Créer le chemin complet du fichier à enregistrer dans le sous-dossier su répertoire public
        Path filepath = Paths.get(subDirectory.toString(), slugifyOriginalFilename + '.' + fileExtension);

        // Lire le contenu du fichier en bytes
        byte[] imageContent = file.getBytes();

        try (OutputStream os = Files.newOutputStream(filepath)) {
            // Écrire le contenu du fichier dans le chemin spécifié
            os.write(imageContent);
        } catch (IOException e) {
            // En cas d'erreur lors de l'écriture du fichier, afficher la trace d'erreur
            e.printStackTrace();
        }

        // Le fichier a été enregistré avec succès, retourner le nom du fichier pour le stocker dans la base de données
        return slugifyOriginalFilename + '.' + fileExtension;

    }
}
