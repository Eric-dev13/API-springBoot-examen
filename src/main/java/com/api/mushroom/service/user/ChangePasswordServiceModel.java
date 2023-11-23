package com.api.mushroom.service.user;

public record ChangePasswordServiceModel(
        String password,
        String newPassword
) {
}
