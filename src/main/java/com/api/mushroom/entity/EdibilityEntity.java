package com.api.mushroom.entity;

import com.github.slugify.Slugify;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    // RELATION AVEC MUSHROOM
    /*
    @OneToMany(mappedBy = "edibilityEntity", orphanRemoval = true)
    private List<MushroomEntity> mushroomEntities = new ArrayList<>();

    public List<MushroomEntity> getMushroomEntities() {
        return mushroomEntities;
    }

    public void setMushroomEntities(List<MushroomEntity> mushroomEntities) {
        this.mushroomEntities = mushroomEntities;
    }
     */

    @PrePersist
    public void prePresist(){
        // Générer automatiquement un slug (identifiant unique texte remplacant l'id dans l'url) avant la mise à jour de base de donnée.
        final Slugify slg = Slugify.builder().build();
        this.slug = slg.slugify(this.name);
    }

}
