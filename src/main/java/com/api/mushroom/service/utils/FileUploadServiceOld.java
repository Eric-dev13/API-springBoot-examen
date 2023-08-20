package com.api.mushroom.service.utils;

import com.api.mushroom.service.utils.UniqueSlugService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
public class FileUploadServiceOld {

    private static UniqueSlugService uniqueSlugService;

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

        // Créez un objet JSON simple pour la réponse
        String jsonResponse = "{\"message\" : \" Fichier téléchargé avec succès. \"}";

        // Le fichier a été enregistré avec succès, retourner une réponse OK au format JSON
        return ResponseEntity.ok("Fichier téléchargé avec succès.");

    }


    public void uploadFile(String sourceFilePath) {
        // Chemin du fichier source a uploader
        //String sourceFilePath = "chemin/du/fichier/source.txt";

        // Répertoire de destination pour stocker le fichier uploader
        String destinationDirectory = "chemin/du/repertoire/destination/";

        // Nom du fichier de destination (le même nom que le fichier source ou un nouveau nom)
        UniqueSlugService uniqueSlugService = new UniqueSlugService();
        String destinationFileName = uniqueSlugService.generateUniqueSlug(sourceFilePath);


       // String destinationFileName = "fichier_destination.txt";

        // Appeler la méthode d'upload
        try {
            uploadFile(sourceFilePath, destinationDirectory, destinationFileName);
            System.out.println("Upload de fichier réussi !");
        } catch (IOException e) {
            System.out.println("Erreur lors de l'upload du fichier : " + e.getMessage());
        }
    }

    public static void uploadFile(String sourceFilePath, String destinationDirectory, String destinationFileName) throws IOException {
        // Créer un objet File pour le fichier source
        File sourceFile = new File(sourceFilePath);

        // Créer le répertoire de destination s'il n'existe pas
        File destinationDir = new File(destinationDirectory);
        if (!destinationDir.exists()) {
            destinationDir.mkdirs();
        }

        // Construire le chemin complet du fichier de destination
        String destinationFilePath = destinationDirectory + destinationFileName;
        File destinationFile = new File(destinationFilePath);

        // Utiliser la méthode Files.copy pour uploader le fichier
        Files.copy(sourceFile.toPath(), destinationFile.toPath());
    }

}
