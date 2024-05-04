package com.api.mushroom.controller.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LocalenameDto {
    private Long id;
    private LocalDateTime createdAt;
    private String name;
    private MushroomDto mushroom;
}
