package com.api.mushroom.controller.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class MushroomDto {
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
    private LamellatypeDto lamellatype;
    private EdibilityDto edibility;
    private List<LocalenameDto> localnames;
    private List<MediaDto> medias;
}
