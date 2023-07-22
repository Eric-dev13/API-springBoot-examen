package com.api.mushroom.entity;

import com.github.slugify.Slugify;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "media")
public class MediaEntity {

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

    // RELATIONS MUSHROOM mapping type: bidirectionnal joinColumn
    @ManyToOne
    @JoinColumn(name = "mushroom_id")
    private MushroomEntity mushroomEntity;

    public MushroomEntity getMushroomEntity() {
        return mushroomEntity;
    }

    public void setMushroomEntity(MushroomEntity mushroomEntity) {
        this.mushroomEntity = mushroomEntity;
    }


    @PrePersist
    public void prePresist(){
        // Générer automatiquement un slug (identifiant unique texte remplacant l'id dans l'url) avant la mise à jour de base de donnée.
        final Slugify slg = Slugify.builder().build();
        this.slug = slg.slugify(this.name);
        // Stocker automatiquement la date de création de l'enregistrement en de base de données.
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    // METHODES pour stocker automatiquement la date de mise à jour de l'enregistrement dans la base de données.
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


}
