package com.api.mushroom.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

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
    private MushroomEntity mushroomEntity;


    /* *************************************** */
    /*             JPA PERSISTENCE             */
    /* *************************************** */
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
