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
    //private final Slugify slugify;

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

    @Column(name="commonname", nullable = false)
    private String commonname;

    @Column(name="latinname")
    private String latinname;

    @Column(name="flesh")
    private String flesh;

    @Column(name="hat")
    private String hat;

    @Column(name="lamella")
    private String lamella;

    @Column(name="foot")
    private String foot;

    @Column(name="habitat")
    private String habitat;

    @Column(name="comment")
    private String comment;

    @Column(name="slug")
    private String slug;

    /*
    // RELATIONS
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private UserEntity userEntity ;
    */

    /*
    @OneToMany(mappedBy = "mushroomEntity", fetch = FetchType.LAZY)
    private Set<MediaEntity> mediaEntities;
    */

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lamella") // La cle étrangère
    private LamellatypeEntity lamellaType;
    */

     /*
    @ManyToMany
    @JoinTable(
        name = "mushroom_localname",
        joinColumns = @JoinColumn(name = "id_mushroom"),
        inverseJoinColumns = @JoinColumn(name = "id_localname"))
    private Set<LocalnameEntity> localnames;
      */

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_edibility") // Jointure vers la cle étrangère de la table comestibility
    private EdibilityEntity edibilityEntity;
     */

    // METHODES
    @PrePersist
    public void prePresist(){
        // Enregistre la date au moment de la création d'un enregistrement.
        this.createdAt = LocalDateTime.now();
        // Génère un slug à partir du nom commun (champ obligatoire) au moment de la création d'un enregistrement.
        //this.slug = slugify.slugify(this.commonname);
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


}
