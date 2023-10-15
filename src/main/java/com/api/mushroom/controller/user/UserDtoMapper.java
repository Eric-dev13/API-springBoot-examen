package com.api.mushroom.controller.user;

import com.api.mushroom.service.user.UserServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface UserDtoMapper {
    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    // POST ET PUT
    @Mapping(source = "filename", target = "filename", qualifiedByName = "typeToOptional")
    UserServiceModel UserDTOToUserServiceModel(UserDTO userDTO);

    // GET ALL et GET ONE
    @Mapping(source = "filename", target = "filename", qualifiedByName = "optionalToType")
    UserGetDTO userServiceModelToUserGetDTO(UserServiceModel userServiceModel);

    //     UserDTO userServiceModelToUserDTO(UserServiceModel userServiceModel);


    // METHODE DE TRANSTYPAGE
    @Named("optionalToType")
    default <T> T optionalToType(Optional<T> source) throws Exception {
        return source.orElse(null);
    }

    @Named("typeToOptional")
    default <T> Optional<T> typeToOptional(T source) throws Exception {
        return Optional.ofNullable(source);
    }

}
