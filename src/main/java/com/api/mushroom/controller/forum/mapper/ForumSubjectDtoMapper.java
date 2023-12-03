package com.api.mushroom.controller.forum.mapper;

import com.api.mushroom.controller.forum.dto.ForumSubjectAddDto;
import com.api.mushroom.controller.forum.dto.ForumSubjectDto;
import com.api.mushroom.controller.forum.dto.ForumUserDto;
import com.api.mushroom.entity.ForumSubjectEntity;
import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.service.forum.model.ForumSubjectServiceModel;
import org.mapstruct.*;

/**
 * unmappedTargetPolicy : indique que tous les champs cible non mappés doivent être ignorés
 * componentModel : spécifie que le modèle de composant Spring doit être utilisé pour le mappage.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ForumSubjectDtoMapper {

    // GET ALL / GET BY ID

    ForumSubjectDto forumSubjectEntityToForumSubjectDto(ForumSubjectEntity forumSubjectEntity);


    // POST / PUT
    ForumSubjectEntity forumSubjectAddDtoToForumSubjectEntity(ForumSubjectAddDto forumSubjectAddDto);

    ForumUserDto forumUserDtoToUserEntity(UserEntity userEntity);


    /* --------------------------------------------------------------- */
    /*                        MAPPER SERVICE MODEL                     */
    /* --------------------------------------------------------------- */

    // dto => serviceModel - UPDATE
    ForumSubjectServiceModel forumSubjectAddDtoToForumSubjectServiceModel(ForumSubjectAddDto forumSubjectAddDto);

    /*// serviceModel => dto - FIND ALL
    ForumSubjectDto forumSubjectServiceModelToForumSubjectDto(ForumSubjectServiceModel forumSubjectServiceModel);*/

    /*// dto => serviceModel - POST
    ForumSubjectServiceModel forumSubjectAddDtoToForumSubjectServiceModel(ForumSubjectPaginatorDto forumSubjectPaginatorDto);*/

    /*// serviceModel => dto - FIND BY ID
    ForumSubjectPaginatorDto forumSubjectServiceModelToForumSubjectPaginatorDto(ForumSubjectServiceModel forumSubjectServiceModel);*/

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    ForumSubjectServiceModel partialUpdate(ForumSubjectAddDto forumSubjectAddDto, @MappingTarget ForumSubjectServiceModel forumSubjectServiceModel );

}