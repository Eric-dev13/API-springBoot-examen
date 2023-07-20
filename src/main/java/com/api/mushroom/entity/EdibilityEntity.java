package com.api.mushroom.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "edibility")
public class EdibilityEntity {
    // DECLARATION DES ATTRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-incrémente
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="path")
    private String path;

    @Column(name="slug")
    private String slug;

    /*
    // ASSOCIATION
    @OneToMany(mappedBy = "lamellaType", fetch = FetchType.LAZY)
    private Set<MushroomEntity> mushroomEntities;
     */

}
