package com.api.mushroom.service.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MushroomServiceModel {
    private Long id;
    private LocalDateTime createdAt;
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
    private LamellatypeServiceModel lamellatype;
    private EdibilityServiceModel edibility;
    private List<LocalenameServiceModel> localnames;
    private List<MediaServiceModel> medias;
}
