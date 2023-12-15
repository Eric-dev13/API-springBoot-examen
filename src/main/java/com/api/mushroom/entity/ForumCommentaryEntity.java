package com.api.mushroom.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "forum_commentary")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class ForumCommentaryEntity {

    /* ************************************* */
    /*      DECLARATION DES PROPRIETES       */
    /* ************************************* */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "commentary", columnDefinition = "LONGTEXT")
    private String commentary;


    /* ******************************************** */
    /*          DECLARATION DES PROPRIETES          */
    /*           RELATIONS / ASSOCIATIONS           */
    /* ******************************************** */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Getter(AccessLevel.NONE) // Si supprime fait planter
    @ManyToOne
    @JoinColumn(name = "forum_subject_id")
    private ForumSubjectEntity forumSubject;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}
