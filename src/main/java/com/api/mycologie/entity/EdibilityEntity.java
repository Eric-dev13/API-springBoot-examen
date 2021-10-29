package com.api.mycologie.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    // ASSOCIATION
    @OneToMany(mappedBy = "lamellaType", fetch = FetchType.LAZY)
    private Set<MushroomEntity> mushroomEntities;

}
