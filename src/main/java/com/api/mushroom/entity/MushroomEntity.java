package com.api.mushroom.entity;


import com.api.mushroom.service.utils.UniqueSlugService;
import com.github.slugify.Slugify;
import lombok.Data;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@Table(name = "mushroom")
@NamedQueries({
    @NamedQuery(name = "MushroomEntity.findAllByVisibility", query = "SELECT m FROM MushroomEntity m WHERE m.visibility = :visibility ORDER BY commonname"),
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
    private LamellatypeEntity lamellatype;

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
