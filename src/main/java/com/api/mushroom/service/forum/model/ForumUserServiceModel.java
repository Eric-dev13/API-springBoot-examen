package com.api.mushroom.service.forum.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ForumUserServiceModel {
    private Long id;
    private LocalDateTime createdAt;
    private String pseudo;
    private String filename;
    private String email;
}
