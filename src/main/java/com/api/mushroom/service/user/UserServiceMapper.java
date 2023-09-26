package com.api.mushroom.service.user;


import com.api.mushroom.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserServiceMapper {
    UserServiceMapper INSTANCE = Mappers.getMapper(UserServiceMapper.class);

    @Mapping(source = "username", target = "email")
    UserServiceModel userEntityToUserServiceMapper(UserEntity userEntity);
}
