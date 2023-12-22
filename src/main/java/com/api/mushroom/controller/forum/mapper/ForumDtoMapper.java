package com.api.mushroom.controller.forum.mapper;

import com.api.mushroom.controller.dto.ForumCategoryDto;

import com.api.mushroom.controller.forum.dto.ForumCategoryGetDto;
//import com.api.mushroom.controller.forum.dto.ForumCategoryDto;
import com.api.mushroom.controller.forum.dto.ForumCommentaryDto;
import com.api.mushroom.controller.forum.dto.ForumSubjectDto;
import com.api.mushroom.controller.forum.dto.ForumSubjectAddDto;
import com.api.mushroom.controller.forum.dto.ForumUserDto;

import com.api.mushroom.entity.ForumSubjectEntity;
import com.api.mushroom.entity.UserEntity;

import com.api.mushroom.service.forum.model.ForumCategoryServiceModel;
import com.api.mushroom.service.forum.model.ForumSubjectFullServiceModel;
import com.api.mushroom.service.forum.model.ForumCommentaryServiceModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ForumDtoMapper {
    // dto --> service
    ForumCategoryServiceModel forumCategoryDtoToForumCategoryServiceModel(ForumCategoryDto forumCategoryDto);

    // service --> dto
    ForumCategoryGetDto forumCategoryServiceModelToForumCategoryGetDto(ForumCategoryServiceModel forumCategoryServiceModel);

    // dto --> service
    @Mapping(target="forumSubject.forumCommentaries", ignore = true)
    ForumCommentaryServiceModel forumCommentaryDtoToForumCommentaryServiceModel(ForumCommentaryDto forumCommentaryDto);

    // service --> dto
    ForumCommentaryDto forumCommentaryServiceModelToForumCommentaryDto(ForumCommentaryServiceModel forumCommentaryServiceModel);

    // repository --> service
    //@Mapping(target="forumCommentaries.forumSubject", ignore = true) // list forumCommentaries ne marche
    ForumSubjectDto forumSubjectEntityToForumSubjectDto(ForumSubjectEntity forumSubjectEntity);

    // dto  --> repository
    ForumSubjectEntity forumSubjectAddDtoToForumSubjectEntity(ForumSubjectAddDto forumSubjectAddDto);

    ForumUserDto forumUserDtoToUserEntity(UserEntity userEntity);

    /* --------------------------------------------------------------- */
    /*                        MAPPER SERVICE MODEL                     */
    /* --------------------------------------------------------------- */

    // dto => serviceModel - UPDATE
    ForumSubjectFullServiceModel forumSubjectAddDtoToForumSubjectServiceModel(ForumSubjectAddDto forumSubjectAddDto);

    // serviceModel => dto - FIND ALL
    @Mapping(target="user", ignore = true)
    @Mapping(target="forumCommentaries", ignore = true)
    ForumSubjectDto forumSubjectServiceModelToForumSubjectDto(ForumSubjectFullServiceModel forumSubjectFullServiceModel);
}
