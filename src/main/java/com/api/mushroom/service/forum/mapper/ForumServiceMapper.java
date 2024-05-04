package com.api.mushroom.service.forum.mapper;

import com.api.mushroom.entity.ForumCategoryEntity;
import com.api.mushroom.entity.ForumCommentaryEntity;
import com.api.mushroom.entity.ForumSubjectEntity;
import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.service.forum.model.*;
//import com.api.mushroom.service.model.ForumSubjectServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ForumServiceMapper {
    // service --> repository
    ForumCategoryEntity forumCategoryServiceModelToForumCategoryEntity(ForumCategoryServiceModel forumCategoryServiceModel);

    // repository --> service
//    @Mapping(target = "forumSubjects", ignore = true)
    ForumCategoryServiceModel forumCategoryEntityToForumCategoryServiceModel(ForumCategoryEntity forumCategoryEntity);


    // service --> repository
    ForumCommentaryEntity addForumCategoryServiceModelToForumCategoryEntity(ForumCommentaryServiceModel forumCommentaryServiceModel);


    // service --> repository
    ForumSubjectEntity forumSubjectServiceModelToForumSubjectEntity(ForumSubjectFullServiceModel forumSubjectFullServiceModel);


    // repository --> service
    ForumSubjectServiceModel forumSubjectEntityToForumSubjectServiceModel(ForumSubjectEntity forumSubjectEntity);


    // repository --> service
//    @Mapping(target="forumCategories", ignore = true)
//    @Mapping(target="forumCommentaries", ignore = true)
//    @Mapping(target="user", ignore = true)
//    ForumSubjectServiceModel forumSubjectEntityToForumSubjectServiceModels(ForumSubjectEntity forumSubjectEntity);

    // repository --> service
//    @Mapping(target="forumSubjects", ignore = true)
//    ForumCategoryServiceModel f(ForumCategoryEntity forumCategoryEntity);

    ForumUserServiceModel userEntityToForumUserServiceModel(UserEntity userEntity);

}
