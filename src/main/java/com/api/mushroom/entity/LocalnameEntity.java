package com.api.mushroom.entity;

import com.github.slugify.Slugify;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "localname")
public class LocalnameEntity {
    // DECLARATION DES ATTRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-incrémente
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="slug", length =255, unique = true)
    private String slug;

    /*
    // RELATION (table de jointure)
    @ManyToMany
    Set<MushroomEntity> mushroomEntities;
     */

    // METHODES pour générer automatiquement un slug (identifiant unique texte remplacant l'id dans l'url) avant la mise à jour de base de donnée.
    /*
    @PrePersist
    public void SlugGenerator() {
        final Slugify slugify = Slugify.builder().build();
        this.slug = slugify.slugify(this.name);
    }
     */

}
