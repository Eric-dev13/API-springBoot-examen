package com.api.mushroom.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "localname")
public class LocalnameEntity {

    /* ************************************* */
    /*      DECLARATION DES PROPRIETES       */
    /* ************************************* */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="name")
    private String name;

    /* ******************************************** */
    /*          DECLARATION DES PROPRIETES          */
    /*           RELATIONS / ASSOCIATIONS           */
    /* ******************************************** */
    // RELATIONS MUSHROOM - mapping type: bidirectionnel
    // Désactiver la génération des méthodes getter ou setter.
    @Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "mushroom_id")
    private MushroomEntity mushroom;


    /* *************************************** */
    /*             JPA PERSISTENCE             */
    /* *************************************** */
    @PrePersist
    public void prePresist(){
        this.createdAt = LocalDateTime.now();
    }

}
