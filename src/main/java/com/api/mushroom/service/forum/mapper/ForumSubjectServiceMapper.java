package com.api.mushroom.service.forum.mapper;

import com.api.mushroom.entity.ForumSubjectEntity;
import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.service.forum.model.ForumSubjectServiceModel;
import com.api.mushroom.service.forum.model.ForumUserServiceModel;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ForumSubjectServiceMapper {

    // POST / PUT
    ForumSubjectEntity forumSubjectServiceModelToForumSubjectEntity(ForumSubjectServiceModel forumSubjectServiceModel);

    // GET ALL / GET BY ID
    ForumSubjectServiceModel forumSubjectEntityToForumSubjectServiceModel(ForumSubjectEntity forumSubjectEntity);

    ForumUserServiceModel userEntityToForumUserServiceModel(UserEntity userEntity);

}