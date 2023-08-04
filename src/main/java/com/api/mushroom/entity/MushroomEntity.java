package com.api.mushroom.entity;


import com.github.slugify.Slugify;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "mushroom")
@NamedQueries({
    @NamedQuery(name = "MushroomEntity.findAllByVisibility", query = "SELECT m FROM MushroomEntity m WHERE m.visibility = :visibility"),
    @NamedQuery(name = "MushroomEntity.findAllTitleImageEdibilityByVisibility", query = "SELECT m.commonname as commonname, m.medias as medias, m.edibility as edibility FROM MushroomEntity m WHERE m.visibility = :visibility"),
    @NamedQuery(name = "MushroomEntity.findBySlug", query="SELECT m FROM MushroomEntity m WHERE m.slug=:slug")
})
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

    // RELATIONS LOCALNAME
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "mushroom_id")
    private Set<LocalnameEntity> localnames = new LinkedHashSet<>();

    // RELATIONS EDIBILITY
    @ManyToOne
    @JoinColumn(name = "edibility_id")
    private EdibilityEntity edibility;

    // RELATIONS MEDIAS - mapping type: bidirectionnel
    @OneToMany(mappedBy = "mushroomEntity",cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<MediaEntity> medias = new ArrayList<>();


    // methodes LAMELLA TYPE
    public LamellatypeEntity getLamellatypeEntity() {
        return lamellatypeEntity;
    }

    public void setLamellatypeEntity(LamellatypeEntity lamellatypeEntity) {
        this.lamellatypeEntity = lamellatypeEntity;
    }

    // methodes LOCALNAME
    public Set<LocalnameEntity> getLocalnames() {
        return localnames;
    }

    public void setLocalnames(Set<LocalnameEntity> localnames) {
        this.localnames = localnames;
    }


    // methodes EDIBILITY
    public EdibilityEntity getEdibility() {
        return edibility;
    }

    public void setEdibilityEntity(EdibilityEntity edibility) {
        this.edibility = edibility;
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
