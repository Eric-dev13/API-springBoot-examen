package com.api.mushroom.controller.user;

public record CurrentUserUpdateDto(
        String pseudo,
        String lastname,
        String firstname,
        String filename
) {
}
