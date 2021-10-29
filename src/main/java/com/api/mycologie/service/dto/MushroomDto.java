package com.api.mycologie.service.dto;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class MushroomDto {
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
}
