package com.api.mushroom.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EdibilityServiceModel {
    private Long id;
    private LocalDateTime createdAt;
    private String name;
    private String filename;
}
