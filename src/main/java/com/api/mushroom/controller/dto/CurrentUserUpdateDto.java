package com.api.mushroom.controller.dto;

public record CurrentUserUpdateDto(
        String pseudo,
        String lastname,
        String firstname,
        String filename
) {
}
