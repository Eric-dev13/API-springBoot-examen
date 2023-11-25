package com.api.mushroom.service.forum.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ForumUserServiceModel {
    private Long id;
    private LocalDateTime createdAt;
    private String pseudo;
    private String filename;
}
