package com.api.mushroom.service.utils;

import com.api.mushroom.service.utils.UniqueSlugService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RequiredArgsConstructor
@Service
public class FileUploadService {

    private static UniqueSlugService uniqueSlugService;

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
