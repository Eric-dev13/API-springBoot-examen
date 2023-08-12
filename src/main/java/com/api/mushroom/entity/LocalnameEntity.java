package com.api.mushroom.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "localname")
public class LocalnameEntity {

    /* ************************************* */
    /*      DECLARATION DES PROPRIETES       */
    /* ************************************* */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne
    @JoinColumn(name = "mushroom_id")
    private MushroomEntity mushroomEntity;


    /* ******************************************** */
    /*                 CONSTRUCTEUR                 */
    /* ******************************************** */
    public LocalnameEntity(){

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

    public String getName() {
        return this.name;
    }


    /* ******************************************* */
    /*                   SETTERS                   */
    /* ******************************************* */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setMushroomEntity(MushroomEntity mushroomEntity) {
        this.mushroomEntity = mushroomEntity;
    }


    /* *************************************** */
    /*             JPA PERSISTENCE             */
    /* *************************************** */
    @PrePersist
    public void prePresist(){
        this.createdAt = LocalDateTime.now();
    }


}
