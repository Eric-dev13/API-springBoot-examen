package com.api.mushroom.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MushroomsPaginateDto {
    private List<MushroomDto> mushrooms;
    private Long totalMushroom;


}
