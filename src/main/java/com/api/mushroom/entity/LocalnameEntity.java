package com.api.mushroom.entity;

import com.github.slugify.Slugify;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "localname")
public class LocalnameEntity {
    // DECLARATION DES ATTRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created_at")
    private LocalDateTime createdAt;


    @Column(name="name")
    private String name;

    @PrePersist
    public void prePresist(){
        this.createdAt = LocalDateTime.now();
    }

}
