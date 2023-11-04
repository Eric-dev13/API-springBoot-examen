package com.api.mushroom.service.utils;

import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UniqueSlugService {

    // Définir un bean pour la classe MyService
    public String generateUniqueSlug(String name) {
        // Générer automatiquement un slug en utilisant l'identifiant unique avant la mise à jour de la base de données.
        // Générer un identifiant universellement unique.
        UUID uniqueID = UUID.randomUUID();
        String uniqueIDToStr = uniqueID.toString();
        // Supprime les tirets de l'identifiant universel unique convertit en chaine de caractère avec la méthode replace.
        String searchString = "-";
        String replacementString = "";
        String randomUniqueId = uniqueIDToStr.replace(searchString, replacementString);
        // Bibliothèque simple et légère qui permet de générer des slugs à partir de chaînes de caractères.
        final Slugify slg = Slugify.builder().build();
        // on concatène le nom de l'espèce et le numero
        return slg.slugify(name + "-" + randomUniqueId);
    }
}
