package com.api.mushroom.entity;

import com.github.slugify.Slugify;
import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "lamellaType")
public class LamellatypeEntity {

    // DECLARATION DES ATTRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-incrémente
    private Long id;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="name")
    private String name;

    @Column(name="path")
    private String path;

    @Column(name="slug", length =255, unique = true)
    private String slug;


    @OneToMany(mappedBy = "lamellatypeEntity", orphanRemoval = true)
    private Set<MushroomEntity> mushroomEntities = new LinkedHashSet<>();

    public Set<MushroomEntity> getMushroomEntities() {
        return mushroomEntities;
    }

    public void setMushroomEntities(Set<MushroomEntity> mushroomEntities) {
        this.mushroomEntities = mushroomEntities;
    }

    // METHODES pour stocker automatiquement la date de création de l'enregistrement en de base de données.
    @PrePersist
    public void prePresist(){
        // Enregistre la date au moment de la création d'un enregistrement.
        this.createdAt = LocalDateTime.now();
    }

    // METHODES pour stocker automatiquement la date de mise à jour de l'enregistrement dans la base de données.
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // METHODES pour générer automatiquement un slug (identifiant unique texte remplacant l'id dans l'url) avant la mise à jour de base de donnée.
    /*
    @PrePersist
    public void SlugGenerator() {
        final Slugify slugify = Slugify.builder().build();
        this.slug = slugify.slugify(this.name);
    }
     */

}
