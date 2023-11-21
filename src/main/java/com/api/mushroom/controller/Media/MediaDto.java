package com.api.mushroom.controller.Media;

import java.time.LocalDateTime;

public record MediaDto(
        Long id,
        LocalDateTime createdAt,
        String name,
        String filename
        //MushroomEntity mushroom
) {
}
