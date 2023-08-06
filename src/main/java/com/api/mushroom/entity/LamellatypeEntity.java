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


    @Column(name="name")
    private String name;

    @Column(name="path")
    private String path;


    @OneToMany(mappedBy = "lamellatype", orphanRemoval = true)
    private Set<MushroomEntity> mushroom = new LinkedHashSet<>();


    // METHODES pour stocker automatiquement la date de création de l'enregistrement en de base de données.
    @PrePersist
    public void prePresist(){
        // Enregistre la date au moment de la création d'un enregistrement.
        this.createdAt = LocalDateTime.now();
    }


}
