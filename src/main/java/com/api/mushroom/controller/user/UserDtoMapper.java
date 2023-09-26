package com.api.mushroom.controller.user;

import com.api.mushroom.service.user.UserServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDtoMapper {
    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    UserDTO userServiceModelToUserDTO(UserServiceModel userServiceModel);
    UserServiceModel UserDTOToUserServiceModel(UserDTO userDTO);
}
