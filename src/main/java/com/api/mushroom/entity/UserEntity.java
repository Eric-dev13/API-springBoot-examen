package com.api.mushroom.entity;

import com.api.mushroom.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity implements Serializable, UserDetails {
    // DECLARATION DES ATTRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-incrémente
    private Long id;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at", nullable = true)
    private LocalDateTime updatedAt;

    // @Column(name = "role")
    // private String role;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "pseudo", length = 255)
    private String pseudo;

    @Column(name = "lastname", length = 255)
    private String lastname;

    @Column(name = "firstname", length = 255)
    private String firstname;

    @Column(name = "email", length = 255, unique = true)
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    // RELATIONS
    /* @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private Set<MushroomEntity> mushroomEntities; */


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
