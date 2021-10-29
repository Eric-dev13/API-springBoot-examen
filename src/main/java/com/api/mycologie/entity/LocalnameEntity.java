package com.api.mycologie.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "localname")
public class LocalnameEntity {
    // DECLARATION DES ATTRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-incrémente
    private Long id;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="name")
    private String name;

    // RELATION (table de jointure)
    @ManyToMany
    Set<MushroomEntity> mushroomEntities;

    // METHODES
    @PrePersist
    public void prePresist(){
        this.createdAt = LocalDateTime.now();
    }
}
