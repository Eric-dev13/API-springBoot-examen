package com.api.mushroom.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Table(name = "forum_subject")
public class ForumSubjectEntity {

    /* ************************************* */
    /*      DECLARATION DES PROPRIETES       */
    /* ************************************* */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-incrémente
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;


    /* ******************************************** */
    /*          DECLARATION DES PROPRIETES          */
    /*           RELATIONS / ASSOCIATIONS           */
    /* ******************************************** */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany(mappedBy = "forumSubjects")
    private Set<ForumCategory> forumCategories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "forumSubject", orphanRemoval = true)
    // @OrderBy("order.createdAt DESC")
    private List<ForumCommentaryEntity> forumCommentaries = new ArrayList<>();

    /* *************************************** */
    /*             JPA PERSISTENCE             */
    /* *************************************** */
    @PrePersist
    public void prePresist() {
        // Stocker automatiquement la date de création de l'enregistrement en de base de données.
        this.createdAt = LocalDateTime.now();
    }

}


