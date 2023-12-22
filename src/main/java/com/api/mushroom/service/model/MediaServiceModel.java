package com.api.mushroom.service.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MediaServiceModel {
    private Long id;
    private LocalDateTime createdAt;
    private String name;
    private String filename;
    private MushroomServiceModel mushroom;
}
