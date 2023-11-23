package com.api.mushroom.controller.user;

import com.api.mushroom.service.user.ChangePasswordServiceModel;
import com.api.mushroom.service.user.UserServiceModel;
import org.mapstruct.*;

/***
 * si une propriété dans l'objet source n'est pas mappée (ayant le meme nom et le meme type) à une propriété dans l'objet cible, MapStruct l'ignorera et la valeur existante de la propriété cible sera préservée.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserDtoMapper {

    ChangePasswordServiceModel changePasswordDtoToChangePasswordServiceModel(CurrentUserChangePasswordDto currentUserChangePasswordDto);

    UserServiceModel userProfilDtoToUserServiceModel(CurrentUserProfilDto currentUserProfilDto);

    UserServiceModel currentUserUpdateDtoToUserServiceModel(CurrentUserUpdateDto currentUserUpdateDto);

    CurrentUserProfilDto userServiceModelToUserProfilDto(UserServiceModel userServiceModel);

    @Mappings({
        @Mapping(source = "email", target = "username"),
        @Mapping(source = "authorities", target = "roles")
    })
    UserSessionStorageDTO userServiceModelToUserSessionStorageDto(UserServiceModel userServiceModel);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CurrentUserProfilDto partialUpdate(UserServiceModel userServiceModel, @MappingTarget CurrentUserProfilDto currentUserProfilDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CurrentUserUpdateDto partialUpdate(UserServiceModel userServiceModel, @MappingTarget CurrentUserUpdateDto currentUserUpdateDto);

}
