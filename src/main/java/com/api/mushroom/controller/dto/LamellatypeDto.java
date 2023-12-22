package com.api.mushroom.controller.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class LamellatypeDto {
    private Long id;
    private LocalDateTime createdAt;
    private String name;
    private String filename;
    private List<MushroomDto> mushroom;
}
