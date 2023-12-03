package com.api.mushroom.controller.user.dto;

public record CurrentUserUpdateDto(
        String pseudo,
        String lastname,
        String firstname,
        String filename
) {
}
