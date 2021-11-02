package com.api.mycologie.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
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
}
