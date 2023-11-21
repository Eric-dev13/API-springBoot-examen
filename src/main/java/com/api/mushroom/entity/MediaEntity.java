package com.api.mushroom.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "media")
public class MediaEntity {

    /* ************************************* */
    /*      DECLARATION DES PROPRIETES       */
    /* ************************************* */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-incrémente
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="name")
    private String name;

    @Column(name="filename")
    private String filename;

    /* ******************************************** */
    /*          DECLARATION DES PROPRIETES          */
    /*           RELATIONS / ASSOCIATIONS           */
    /* ******************************************** */
    // RELATIONS MUSHROOM - mapping type: bidirectionnel
    @ManyToOne
    // Désactiver la génération des méthodes getter ou setter.
    @Getter(AccessLevel.NONE)
    @JoinColumn(name = "mushroom_id")
    private MushroomEntity mushroom;


    /* *************************************** */
    /*             JPA PERSISTENCE             */
    /* *************************************** */
    @PrePersist
    public void prePresist(){
        // Stocker automatiquement la date de création de l'enregistrement en de base de données.
        this.createdAt = LocalDateTime.now();
    }

    @PostRemove
    public void fileDelete() {
        // Récupérer le répertoire public absolu
        Path publicDirectory = Paths.get(".", "public/upload/mushrooms").toAbsolutePath();
        // Retourne l'acces au fichier
        Path filepath = Paths.get(publicDirectory.toString(), this.filename);
        try {
            Files.delete(filepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
