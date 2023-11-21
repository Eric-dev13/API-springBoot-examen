package com.api.mushroom.service.user;

import com.api.mushroom.entity.UserEntity;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserServiceMapper {
    UserServiceModel userEntityToUserServiceModel(UserEntity userEntity);
    UserEntity userServiceModelToUserEntity(UserServiceModel userServiceModel);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    UserEntity partialUpdate(UserServiceModel userServiceModel, @MappingTarget UserEntity userEntity);


}