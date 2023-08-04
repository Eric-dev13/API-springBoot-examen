package com.api.mushroom.service.dto;

import com.api.mushroom.entity.MediaEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class MushroomDTO {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean visibility;
    private String commonname;
    private String latinname;
    private String hat;
    private String lamella;
    private String foot;
    private String habitat;
    private String comment;
    private String slug;
    private List<MediaEntity> medias = new ArrayList<>();
}
