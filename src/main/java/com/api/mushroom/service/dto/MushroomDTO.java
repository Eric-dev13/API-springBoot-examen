package com.api.mushroom.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

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
}
