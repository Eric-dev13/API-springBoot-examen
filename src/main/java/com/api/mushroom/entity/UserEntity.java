package com.api.mushroom.entity;

import com.api.mushroom.security.Role;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class UserEntity implements Serializable, UserDetails {

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

    @Column(name = "email", length = 255, unique = true)
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "pseudo", length = 255, nullable = false)
    private String pseudo;

    @Column(name = "lastname", length = 255)
    private String lastname;

    @Column(name = "firstname", length = 255)
    private String firstname;

    @Column(name = "filename", length = 255)
    private String filename;

    @Column(name = "is_verified")
    private boolean isVerified;

    /* ******************************************** */
    /*          DECLARATION DES PROPRIETES          */
    /*           RELATIONS / ASSOCIATIONS           */
    /* ******************************************** */

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<ForumSubjectEntity> forumSubjects = new ArrayList<>();


    @OneToMany(mappedBy = "user", orphanRemoval = true)
//    @OrderBy("order.createdAt.DESC")
    private List<ForumCommentaryEntity> forumCommentaries = new ArrayList<>();



    /* *************************************** */
    /*             JPA PERSISTENCE             */
    /* *************************************** */

    // METHODES pour stocker automatiquement la date de création de l'enregistrement en de base de données.
    @PrePersist
    public void prePresist(){
        // stocker automatiquement la date de création de l'enregistrement en de base de données.
        this.createdAt = LocalDateTime.now();
    }

    // METHODES pour stocker automatiquement la date de mise à jour de l'enregistrement dans la base de données.
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }



    /* *************************************** */
    /*          IMPLEMENT USER DETAIL          */
    /* *************************************** */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername(){
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return this.id + " : " + this.lastname + " " + this.firstname + " " + this.email;
    }


}
