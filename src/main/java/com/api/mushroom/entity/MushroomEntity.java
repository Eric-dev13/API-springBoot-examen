package com.api.mushroom.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.github.slugify.Slugify;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 *
 */
@Entity
@Data
@Table(name = "mushroom")
@NamedQuery(name = "MushroomEntity.findBySlug", query = "SELECT m FROM MushroomEntity m WHERE m.slug=:slug")
public class MushroomEntity {
    /* ************************************* */
    /*      DECLARATION DES PROPRIETES       */
    /* ************************************* */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-incrémente
    private Long id;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="visibility",columnDefinition = "tinyint" )
    private boolean visibility;

    @NotBlank(message = "Ce champ est obligatoire !")
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

    /* ******************************************** */
    /*          DECLARATION DES PROPRIETES          */
    /*           RELATIONS / ASSOCIATIONS           */
    /* ******************************************** */
    // ASSOCIER AVEC L'ENTITEE LAMELLA TYPE (cle étrangère est stockée ici)
    @ManyToOne
    @JoinColumn(name = "lamellatype_id")
    private LamellatypeEntity lamellatype;

    // ASSOCIER AVEC L'ENTITEE EDIBILITY - (cle étrangère est stockée ici)
    @ManyToOne
    @JoinColumn(name = "edibility_id")
    private EdibilityEntity edibility;

    // ASSOCIE AVEC L'ENTITEE LOCALNAME - mapping type: bidirectionnel - (cle étrangère est stockée dans la table associée)
    // Configurée pour propager les opérations de persistance (CascadeType.PERSIST)et de suppression automatiquement des entités enfants lorsqu'elles sont dissociées de l'entité parente (orphanRemoval = true).
    @OneToMany(mappedBy = "mushroom", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<LocalnameEntity> localnames = new ArrayList<>();

    // ASSOCIE AVEC L'ENTITEE MEDIAS - mapping type: bidirectionnel - (cle étrangère est stockée dans la table associée)
    // Configurée pour propager les opérations de persistance (CascadeType.PERSIST)et de suppression automatiquement des entités enfants lorsqu'elles sont dissociées de l'entité parente (orphanRemoval = true).
    @OneToMany(mappedBy = "mushroom", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MediaEntity> medias = new ArrayList<>();


    /* *************************************** */
    /*             JPA PERSISTENCE             */
    /* *************************************** */
    @PrePersist
    public void prePresist(){
        // Générer automatiquement un slug en utilisant l'identifiant unique avant la mise à jour de la base de données.
        // Générer un identifiant universellement unique.
        UUID uniqueID = UUID.randomUUID();
        String uniqueIDToStr = uniqueID.toString();
        // Supprime les tirets de l'identifiant universellement uniques convertit en chaine de caractère avec la méthode replace.
        String searchString = "-";
        String replacementString = "";
        String randomUniqueId = uniqueIDToStr.replace(searchString, replacementString);
        // Bibliothèque simple et légère qui permet de générer des slugs à partir de chaînes de caractères.
        final Slugify slg = Slugify.builder().build();
        // on concatène le nom de l'espèce et le numero
        this.slug = slg.slugify(this.commonname + "-" + randomUniqueId);

        // Stocker automatiquement la date de création de l'enregistrement en de base de données.
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    // METHODES pour stocker automatiquement la date de mise à jour de l'enregistrement dans la base de données.
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
