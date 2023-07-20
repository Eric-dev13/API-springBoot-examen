package com.api.mushroom.entity;


import com.api.mushroom.repository.UserRepository;
import com.github.slugify.Slugify;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "mushroom")
public class MushroomEntity {
    // instanciation de la classe slugify dans le constructeur (injection de dépendance).
    // private final Slugify slugify;
    //final Slugify slugify = Slugify.builder().build();

    // DECLARATION DES ATTRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-incrémente
    private Long id;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="visibility")
    private boolean visibility;

    @Column(name="commonname", length = 255, nullable = false)
    private String commonname;

    @Column(name="latinname", length =255)
    private String latinname;

    @Lob
    @Column(name="flesh", columnDefinition = "LONGTEXT")
    private String flesh;

    @Lob
    @Column(name="hat", columnDefinition = "LONGTEXT")
    private String hat;

    @Lob
    @Column(name="lamella", columnDefinition = "LONGTEXT")
    private String lamella;

    @Lob
    @Column(name="foot", columnDefinition = "LONGTEXT")
    private String foot;

    @Lob
    @Column(name="habitat", columnDefinition = "LONGTEXT")
    private String habitat;

    @Lob
    @Column(name="comment", columnDefinition = "LONGTEXT")
    private String comment;

    @Column(name="slug", length =255, unique = true)
    private String slug;


    @ManyToOne
    @JoinColumn(name = "id_lamella_type")
    private LamellatypeEntity lamellatypeEntity;

    public LamellatypeEntity getLamellatypeEntity() {
        return lamellatypeEntity;
    }

    public void setLamellatypeEntity(LamellatypeEntity lamellatypeEntity) {
        this.lamellatypeEntity = lamellatypeEntity;
    }


    @PrePersist
    public void prePresist(){
        // Générer automatiquement un slug (identifiant unique texte remplacant l'id dans l'url) avant la mise à jour de base de donnée.
        final Slugify slg = Slugify.builder().build();
        this.slug = slg.slugify(this.commonname);
        // stocker automatiquement la date de création de l'enregistrement en de base de données.
        this.createdAt = LocalDateTime.now();
    }

    // METHODES pour stocker automatiquement la date de mise à jour de l'enregistrement dans la base de données.
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // METHODES pour générer automatiquement un slug (identifiant unique texte remplacant l'id dans l'url) avant la mise à jour de base de donnée.

    //@PrePersist
    //public void SlugGenerator() {
        //final Slugify slg = Slugify.builder().build();
        //final String result = slg.slugify("Hello, world!");
        // result: hello-world
    //}



}
