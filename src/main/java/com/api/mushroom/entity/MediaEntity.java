package com.api.mushroom.entity;

import com.github.slugify.Slugify;
import lombok.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
//@Data
@Table(name = "media")
public class MediaEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-incrémente
    private Long id;

    @Getter
    @Setter
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Getter
    @Setter
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Getter
    @Setter
    @Column(name="name")
    private String name;

    @Getter
    @Setter
    @Column(name="path")
    private String path;


    // RELATIONS MUSHROOM - mapping type: bidirectionnel
    @ManyToOne
    @JoinColumn(name = "mushroom_id")
    //@Getter
    @Setter
    private MushroomEntity mushroomEntity;


    @PrePersist
    public void prePresist(){
        // Stocker automatiquement la date de création de l'enregistrement en de base de données.
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    // METHODES pour stocker automatiquement la date de mise à jour de l'enregistrement dans la base de données.
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
