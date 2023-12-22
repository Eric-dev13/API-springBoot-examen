package com.api.mushroom.service.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class LamellatypeServiceModel {
    private Long id;
    private LocalDateTime createdAt;
    private String name;
    private String filename;
    private List<MushroomServiceModel> mushroom;
}
