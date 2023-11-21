package com.api.mushroom.controller.Localname;

import java.time.LocalDateTime;

public record LocalnameDto(
        Long id,
        LocalDateTime createdAt,
        String name
        // MushroomEntity mushroom
) {
}
