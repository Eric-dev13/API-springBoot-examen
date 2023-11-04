package com.api.mushroom.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "forum_commentary")
public class ForumCommentaryEntity {

    /* ************************************* */
    /*      DECLARATION DES PROPRIETES       */
    /* ************************************* */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-incrémente
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "commentary", columnDefinition = "LONGTEXT")
    private String commentary;


    /* ******************************************** */
    /*          DECLARATION DES PROPRIETES          */
    /*           RELATIONS / ASSOCIATIONS           */
    /* ******************************************** */
    // @Getter(AccessLevel.NONE) // ! redondant sans mapper dans un dto
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "forum_subject_id")
    private ForumSubjectEntity forumSubjectEntity;


}
