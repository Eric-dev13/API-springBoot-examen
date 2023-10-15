package com.api.mushroom.controller;

import com.api.mushroom.entity.EdibilityEntity;
import com.api.mushroom.entity.LamellatypeEntity;
import com.api.mushroom.entity.LocalnameEntity;
import com.api.mushroom.entity.MediaEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MushroomGetDto {

    private Long id;
    private LocalDateTime updatedAt;
    private boolean visibility;
    private String commonname;
    private String latinname;
    private String flesh;
    private String hat;
    private String lamella;
    private String foot;
    private String habitat;
    private String comment;
    private String slug;
    private LamellatypeEntity lamellatype;
    private EdibilityEntity edibility;
    private List<LocalnameEntity> localnames = new ArrayList<>();
    private List<MediaEntity> medias = new ArrayList<>();
}
