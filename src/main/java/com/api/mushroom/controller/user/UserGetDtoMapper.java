package com.api.mushroom.controller.user;

import com.api.mushroom.service.user.UserServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserGetDtoMapper {
    UserGetDtoMapper INSTANCE = Mappers.getMapper(UserGetDtoMapper.class);

    UserGetDTO userServiceModelToUserGetDTO(UserServiceModel userServiceModel);
    UserServiceModel UserGetDTOToUserServiceModel(UserGetDTO userGetDTO);
}
