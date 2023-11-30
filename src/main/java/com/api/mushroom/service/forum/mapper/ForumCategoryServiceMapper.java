package com.api.mushroom.service.forum.mapper;


import com.api.mushroom.entity.ForumCategoryEntity;
import com.api.mushroom.service.forum.model.ForumCategoryServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ForumCategoryServiceMapper {

    // POST / PUT
    ForumCategoryEntity forumCategoryServiceModelToForumCategoryEntity(ForumCategoryServiceModel forumCategoryServiceModel);

    // GET ALL / GET BY ID
    //@Mapping(target = "forumSubjects", ignore = true)
    ForumCategoryServiceModel forumCategoryEntityToForumCategoryServiceModel(ForumCategoryEntity forumCategoryEntity);

}
