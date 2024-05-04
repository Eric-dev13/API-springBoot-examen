package com.api.mushroom.entity;

import com.github.slugify.Slugify;
import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "lamellaType")
public class LamellatypeEntity {

    /* ************************************* */
    /*      DECLARATION DES PROPRIETES       */
    /* ************************************* */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-incrémente
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
    @OneToMany(mappedBy = "lamellatype", orphanRemoval = true)
    private List<MushroomEntity> mushroom = new ArrayList<>();


    /* *************************************** */
    /*             JPA PERSISTENCE             */
    /* *************************************** */
    // METHODES pour générer la date de création avant l'enregistrement en de base de données.
    @PrePersist
    public void prePresist(){
        this.createdAt = LocalDateTime.now();
    }


}
