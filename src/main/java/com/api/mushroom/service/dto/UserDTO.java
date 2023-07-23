package com.api.mushroom.service.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class UserDTO {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String role;
    private String pseudo;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String avatar;
}
