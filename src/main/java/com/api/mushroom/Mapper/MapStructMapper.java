package com.api.mushroom.Mapper;

import com.api.mushroom.controller.dto.*;
import com.api.mushroom.controller.forum.dto.ForumSubjectAddDto;
import com.api.mushroom.service.model.*;
import com.api.mushroom.entity.*;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MapStructMapper {

//    MapStructMapper INSTANCE = Mappers.getMapper( MapStructMapper.class );

    // ***********************
    //        MUSHROOM
    // ***********************

    // dto --> service
    MushroomServiceModel mushroomDtoToMushroomService(MushroomDto mushroomDto);

    // service --> repository
    MushroomEntity mushroomServiceToMushroomEntity(MushroomServiceModel mushroomServiceModel);

    // repository --> service
    MushroomServiceModel mushroomEntityToMushroomService(MushroomEntity mushroomEntity);

    // service --> dto
    MushroomDto mushroomServiceMushroomDto(MushroomServiceModel mushroomServiceModel);


    // ***********************
    //        EDIBILITY
    // ***********************
    // dto --> service
    EdibilityServiceModel edibilityDtoToEdibilityService(EdibilityDto edibilityDto);

    // service --> repository
    EdibilityEntity edibilityServiceToEdibilityEntity(EdibilityServiceModel edibilityServiceModel);

    // repository --> service
    EdibilityServiceModel edibilityEntityToEdibilityService(EdibilityEntity edibilityEntity);

    // service --> dto
    EdibilityDto edibilityServiceEdibilityDto(EdibilityServiceModel edibilityServiceModel);



    // ***********************
    //      LAMELLE TYPE
    // ***********************
    // dto --> service
    LamellatypeServiceModel lamellatypeDtoToLamellatypeService(LamellatypeDto lamellatypeDto);

    // service --> repository
    LamellatypeEntity lamellatypeServiceToLamellatypeEntity(LamellatypeServiceModel lamellatypeServiceModel);

    // repository --> service
    LamellatypeServiceModel lamellatypeEntityToLamellatypeService(LamellatypeEntity lamellatypeEntity);

    // service --> dto
    LamellatypeDto lamellatypeServiceLamellatypeDto(LamellatypeServiceModel lamellatypeServiceModel);


    // ***********************
    //      LOCALE NAME
    // ***********************
    // dto --> service
    LocalenameServiceModel localenameDtoToLocalenameService(LocalenameDto lamellatypeDto);

    // service --> repository
    LocalnameEntity localenameServiceToLocalenameEntity(LocalenameServiceModel LocalenameServiceModel);

    // repository --> service
    @Mapping(target = "mushroom", ignore = true)
    LocalenameServiceModel localenameEntityToLocalenameService(LocalnameEntity lamellatypeEntity);

    // service --> dto
    LocalenameDto localenameServiceLocalenameDto(LocalenameServiceModel LocalenameServiceModel);


    // ***********************
    //      MEDIA
    // ***********************
    // dto --> service
    MediaServiceModel mediaDtoToMediaService(MediaDto mediaDto);

    // service --> repository
    MediaEntity mediaServiceToMediaEntity(MediaServiceModel mediaServiceModel);

    // repository --> service
    @Mapping(target = "mushroom", ignore = true)
    MediaServiceModel mediaEntityToMediaService(MediaEntity mediaEntity);

    // service --> dto
    MediaDto mediaServiceMediaDto(MediaServiceModel mediaServiceModel);



//    // ***********************
//    //          USER
//    // ***********************
//
//    // dto --> service
//    UserServiceModel UserDtoToUserService(UserDto userDto);
//
//    // service --> repository
//    UserEntity UserServiceToUserEntity(UserServiceModel userServiceModel);
//
//    // repository --> service
//    UserServiceModel UserEntityToUserService(UserEntity userEntity);
//
//    // service --> dto
//    UserDto UserServiceUserDto(UserServiceModel userServiceModel);


    // ***********************
    //      CURRENT USER
    // ***********************
    // dto --> service
    ChangePasswordServiceModel changePasswordDtoToChangePasswordServiceModel(CurrentUserChangePasswordDto currentUserChangePasswordDto);

    // dto --> service
    UserServiceModel userProfilDtoToUserServiceModel(UserDto userDto);

    // dto --> service
    UserServiceModel currentUserUpdateDtoToUserServiceModel(CurrentUserUpdateDto currentUserUpdateDto);

    // service --> repository
    UserEntity userServiceModelToUserEntity(UserServiceModel userServiceModel);

    // repository --> service
    UserServiceModel userEntityToUserServiceModel(UserEntity userEntity);

    // service --> dto bouchonner les agregations de ForumSubjectServiceModel ForumCommentaryServiceModel ?
    CurrentUserProfilDto userServiceModelToUserProfilDto(UserServiceModel userServiceModel);

    // service --> dto
    @Mappings({
            @Mapping(source = "email", target = "username"),
            @Mapping(source = "authorities", target = "roles")
    })
    UserSessionStorageDTO userServiceModelToUserSessionStorageDto(UserServiceModel userServiceModel);

//    // service --> dto // Methode desactivée
//    List<ForumSubjectDto> forumSubjectFullServiceModelsToForumSubjectDtos(List<ForumSubjectServiceModel> forumSubjectServiceModels);


//    // List entity
//    List<ForumSubjectFullServiceModel> forumSubjectEntitiesToForumSubjectServiceModels (List<ForumSubjectEntity> forumSubjectEntityList );
//
//    // repository --> service
//    @Mapping(target="forumCommentaries",ignore = true)
//    UserFullServiceModel userEntityToUserServiceForFindAllSubjects(UserEntity userEntity);





    // ***********************
    //      FORUM CATEGORY
    // ***********************
    // dto --> service
    ForumCategoryServiceModel forumCategoryDtoToForumCategoryService(ForumCategoryDto forumCategoryDto);

    // service --> repository
    ForumCategoryEntity forumCategoryServiceToForumCategoryEntity(ForumCategoryServiceModel forumCategoryServiceModel);

    // repository --> service
    @Mapping(target="forumSubjects", ignore = true)
    ForumCategoryServiceModel forumCategoryEntityToForumCategoryService(ForumCategoryEntity forumCategoryEntity);
//
//    // service --> dto
//    ForumCategoryDto forumCategoryServiceToForumCategoryDto(ForumCategoryServiceModel forumCategoryServiceModel);
//
//
    // ***********************
    //    FORUM COMMENTARY
    // ***********************

    // dto --> service
    ForumCommentaryServiceModel forumCommentaryDtoToServiceModel(ForumCommentaryDto forumCommentaryDto);

    // service --> repository
    ForumCommentaryEntity forumCommentaryServiceToForumCommentaryEntity(ForumCommentaryServiceModel forumCommentaryServiceModel);

    // repository --> service
    @Mapping(target="user", ignore = true)
    ForumCommentaryServiceModel forumCommentaryEntityToForumCommentaryService(ForumCommentaryEntity forumCommentaryEntity);
//
//    // service --> dto
//    ForumCommentaryDto forumCommentaryServiceToForumCommentaryDto(ForumCommentaryServiceModel forumCommentaryServiceModel);
//
//
    // ***********************
    //    FORUM SUBJECT
    // ***********************

    // dto --> service
    ForumSubjectServiceModel forumSubjectDtoToService(ForumSubjectDto forumSubjectAddDto);

    // service --> repository
    ForumSubjectEntity forumSubjectServiceModelToForumSubjectEntity(ForumSubjectServiceModel forumSubjectServiceModel);

    // repository --> service
    @Mapping(target="user", ignore = true)
    ForumSubjectServiceModel forumSubjectEntityToForumSubjectService(ForumSubjectEntity forumSubjectEntity);

    // service --> dto
    ForumSubjectDto forumSubjectServiceToForumSubjectDto(ForumSubjectServiceModel forumSubjectServiceModel);

}
