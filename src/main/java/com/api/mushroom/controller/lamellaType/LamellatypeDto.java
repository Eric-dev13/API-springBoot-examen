package com.api.mushroom.controller.lamellaType;

import java.time.LocalDateTime;

public record LamellatypeDto(
        Long id,
        LocalDateTime createdAt,
        String name,
        String filename
        //Set<MushroomEntity>mushroom
) {
}
