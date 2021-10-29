package com.api.mycologie.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "media")
public class MediaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-incrémente
    private Long id;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="path")
    private String path;

    @Column(name="name")
    private String name;

    // RELATION
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mushroom",nullable = false)
    private MushroomEntity mushroomEntity ;

    // METHODES
    @PrePersist
    public void prePresist(){
        this.createdAt = LocalDateTime.now();
    }

}
