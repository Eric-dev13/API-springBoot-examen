package com.api.mushroom.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangePasswordServiceModel {
    private String password;
    private String newPassword;
}
