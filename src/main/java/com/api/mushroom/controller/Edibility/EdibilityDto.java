package com.api.mushroom.controller.Edibility;

import java.time.LocalDateTime;

public record EdibilityDto(
        Long id,
        LocalDateTime createdAt,
        String name,
        String filename
) {
}
