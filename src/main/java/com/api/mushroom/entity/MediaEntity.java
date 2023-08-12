package com.api.mushroom.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "media")
public class MediaEntity {

    /* ************************************* */
    /*      DECLARATION DES PROPRIETES       */
    /* ************************************* */
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


    /* ******************************************** */
    /*          DECLARATION DES PROPRIETES          */
    /*           RELATIONS / ASSOCIATIONS           */
    /* ******************************************** */
    // RELATIONS MUSHROOM - mapping type: bidirectionnel
    @ManyToOne
    @JoinColumn(name = "mushroom_id")
    private MushroomEntity mushroomEntity;


    /* ******************************************** */
    /*                 CONSTRUCTEUR                 */
    /* ******************************************** */
    public MediaEntity() {
    }


    /* ******************************************* */
    /*                   GETTERS                   */
    /* ******************************************* */
    public Long getId() {
        return this.id;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }


    /* ******************************************* */
    /*                   SETTERS                   */
    /* ******************************************* */
    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setMushroomEntity(MushroomEntity mushroomEntity) {
        this.mushroomEntity = mushroomEntity;
    }


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


    /* ********************************************* */
    /*                   TO STRING                   */
    /* ********************************************* */
    public String toString() {
        return "MediaEntity(id=" + this.getId() + ", createdAt=" + this.getCreatedAt() + ", updatedAt=" + this.getUpdatedAt() + ", name=" + this.getName() + ", path=" + this.getPath();
    }
}
