package com.api.mycologie.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
public class UserEntity {
    // DECLARATION DES ATTRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-incrémente
    private Long id;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "role")
    private String role;

    @Column(name = "pseudo")
    private String pseudo;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    // RELATIONS
    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private Set<MushroomEntity> mushroomEntities;


    // METHODES
   @PrePersist
    public void prePresist(){

       this.createdAt = LocalDateTime.now();
   }

   @PreUpdate
    public void preUpdate() {

       this.updatedAt = LocalDateTime.now();
   }

//   @PreRemove
//   private void preRemove() {
//       this.mushroomEntities.forEach(mushroomEntity -> mushroomEntity.setUserEntity(null));
//   }

}
