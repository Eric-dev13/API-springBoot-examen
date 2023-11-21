package com.api.mushroom.controller.mushroom;

import java.util.List;

public record MushroomsPaginatorDto(
        List<MushroomGetDto> mushrooms,
        Long totalMushroom
) {
}
