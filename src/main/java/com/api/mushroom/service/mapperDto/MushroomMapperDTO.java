package com.api.mushroom.service.mapperDto;

import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.service.dto.MushroomDTO;
import com.api.mushroom.service.dto.UserDTO;

public class MushroomMapperDTO {

    // Le pattern DAO (Data Access Object) permet de faire le lien entre la couche métier et la couche persistante.
    public MushroomDTO entityMappingToDto(MushroomEntity mushroomEntity){
        MushroomDTO mushroomDTO = new MushroomDTO();

        mushroomDTO.setComment(mushroomEntity.getComment());
        mushroomDTO.setId(mushroomEntity.getId());
        mushroomDTO.setCreatedAt(mushroomEntity.getCreatedAt());
        mushroomDTO.setUpdatedAt(mushroomEntity.getUpdatedAt());
        mushroomDTO.setVisibility(mushroomEntity.isVisibility());
        mushroomDTO.setCommonname(mushroomEntity.getCommonname());
        mushroomDTO.setLatinname(mushroomEntity.getLatinname());
        mushroomDTO.setHat(mushroomEntity.getHat());
        mushroomDTO.setLamella(mushroomEntity.getLamella());
        mushroomDTO.setFoot(mushroomEntity.getFoot());
        mushroomDTO.setHabitat(mushroomEntity.getHabitat());
        mushroomDTO.setComment(mushroomEntity.getComment());
        mushroomDTO.setSlug(mushroomEntity.getSlug());

        return mushroomDTO;
    }

    public MushroomEntity dtoMappingToEntity(MushroomDTO mushroomDTO){
        MushroomEntity mushroomEntity = new MushroomEntity();

        mushroomEntity.setComment(mushroomDTO.getComment());
        mushroomEntity.setId(mushroomDTO.getId());
        mushroomEntity.setCreatedAt(mushroomDTO.getCreatedAt());
        mushroomEntity.setUpdatedAt(mushroomDTO.getUpdatedAt());
        mushroomEntity.setVisibility(mushroomDTO.isVisibility());
        mushroomEntity.setCommonname(mushroomDTO.getCommonname());
        mushroomEntity.setLatinname(mushroomDTO.getLatinname());
        mushroomEntity.setHat(mushroomDTO.getHat());
        mushroomEntity.setLamella(mushroomDTO.getLamella());
        mushroomEntity.setFoot(mushroomDTO.getFoot());
        mushroomEntity.setHabitat(mushroomDTO.getHabitat());
        mushroomEntity.setComment(mushroomDTO.getComment());
        mushroomEntity.setSlug(mushroomDTO.getSlug());

        return mushroomEntity;
    }
}
