package com.api.mushroom.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "forum_category")
public class ForumCategory {

    /* ************************************* */
    /*      DECLARATION DES PROPRIETES       */
    /* ************************************* */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-incrémente
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "ForumCategory_forumSubjectEntities",
            joinColumns = @JoinColumn(name = "forumCategory_id"),
            inverseJoinColumns = @JoinColumn(name = "forumSubjectEntities_id"))
    private Set<ForumSubjectEntity> forumSubjectEntities = new LinkedHashSet<>();

}
