package com.api.mushroom.service.mapperDto;

import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserMapperDTO {

    // Le pattern DAO (Data Access Object) permet de faire le lien entre la couche métier et la couche persistante.
    public UserDTO entityMappingToDto(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();

        return userDTO;
    }

    public UserEntity dtoMappingToEntity(UserDTO userDTO){
       UserEntity userEntity = new UserEntity();

        return userEntity;
    }
}