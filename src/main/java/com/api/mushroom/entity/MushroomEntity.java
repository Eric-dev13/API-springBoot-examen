package com.api.mushroom.entity;


import com.github.slugify.Slugify;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "mushroom")
@NamedQuery(name = "MushroomEntity.findAllIsVisibility", query = "SELECT e FROM MushroomEntity e WHERE e.visibility = :visibility")
public class MushroomEntity {

    // =============================
    // = DECLARATION DES ATTRIBUTS =
    // =============================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-incrémente
    private Long id;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="visibility",columnDefinition = "tinyint" )
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

    // RELATIONS LAMELLA TYPE
    @ManyToOne
    @JoinColumn(name = "lamellatype_id")
    private LamellatypeEntity lamellatypeEntity;

    public LamellatypeEntity getLamellatypeEntity() {
        return lamellatypeEntity;
    }

    public void setLamellatypeEntity(LamellatypeEntity lamellatypeEntity) {
        this.lamellatypeEntity = lamellatypeEntity;
    }


    // RELATIONS LOCALNAME
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "mushroom_id")
    private Set<LocalnameEntity> localnameEntities = new LinkedHashSet<>();

    public Set<LocalnameEntity> getLocalnameEntities() {
        return localnameEntities;
    }

    public void setLocalnameEntities(Set<LocalnameEntity> localnameEntities) {
        this.localnameEntities = localnameEntities;
    }


    // RELATIONS MEDIA mapping type: unidirectionnal joinColumn
    // Récupère la collection des médias lié avec l'enreigistrment One To Many

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "mushroom_id")
    private Set<MediaEntity> mediaEntities = new LinkedHashSet<>();

    public Set<MediaEntity> getMediaEntities() {
        return mediaEntities;
    }

    public void setMediaEntities(Set<MediaEntity> mediaEntities) {
        this.mediaEntities = mediaEntities;
    }



    // RELATIONS MEDIA mapping type: bidirectionnal joinColumn
    @OneToMany(mappedBy = "mushroomEntity", orphanRemoval = true)
    private Set<MediaEntity> mediaEntitiesBidirectionnel = new LinkedHashSet<>();

    public Set<MediaEntity> getMediaEntitiesBidirectionnel() {
        return mediaEntitiesBidirectionnel;
    }

    public void setMediaEntitiesBidirectionnel(Set<MediaEntity> mediaEntitiesBidirectionnel) {
        this.mediaEntitiesBidirectionnel = mediaEntitiesBidirectionnel;
    }


    // RELATIONS EDIBILITY
    @ManyToOne
    @JoinColumn(name = "edibility_id")
    private EdibilityEntity edibilityEntity;

    public EdibilityEntity getEdibilityEntity() {
        return edibilityEntity;
    }

    public void setEdibilityEntity(EdibilityEntity edibilityEntity) {
        this.edibilityEntity = edibilityEntity;
    }




    @PrePersist
    public void prePresist(){
        // Générer automatiquement un slug (identifiant unique texte remplacant l'id dans l'url) avant la mise à jour de base de donnée.
        final Slugify slg = Slugify.builder().build();
        this.slug = slg.slugify(this.commonname);
        // Stocker automatiquement la date de création de l'enregistrement en de base de données.
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    // METHODES pour stocker automatiquement la date de mise à jour de l'enregistrement dans la base de données.
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
