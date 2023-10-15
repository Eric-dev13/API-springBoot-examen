package com.api.mushroom.service.user;


import com.api.mushroom.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface UserServiceMapper {
    UserServiceMapper INSTANCE = Mappers.getMapper(UserServiceMapper.class);

    @Mappings({
    @Mapping(source = "username", target = "email"),
    @Mapping(source = "filename", target = "filename", qualifiedByName = "typeToOptional")
    })
    UserServiceModel userEntityToUserServiceMapper(UserEntity userEntity);


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