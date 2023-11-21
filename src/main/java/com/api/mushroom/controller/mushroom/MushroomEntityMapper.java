package com.api.mushroom.controller.mushroom;

import com.api.mushroom.entity.MushroomEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MushroomEntityMapper {
    MushroomEntity toEntity(MushroomGetDto mushroomGetDto);

    @AfterMapping
    default void linkLocalnames(@MappingTarget MushroomEntity mushroomEntity) {
        mushroomEntity.getLocalnames().forEach(localname -> localname.setMushroom(mushroomEntity));
    }

    @AfterMapping
    default void linkMedias(@MappingTarget MushroomEntity mushroomEntity) {
        mushroomEntity.getMedias().forEach(media -> media.setMushroom(mushroomEntity));
    }

    MushroomGetDto toDto(MushroomEntity mushroomEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MushroomEntity partialUpdate(MushroomGetDto mushroomGetDto, @MappingTarget MushroomEntity mushroomEntity);
}