package com.api.mushroom.controller.forum.mapper;

import com.api.mushroom.controller.forum.dto.ForumCategoryDto;
import com.api.mushroom.entity.ForumCategoryEntity;
import com.api.mushroom.service.forum.model.ForumCategoryServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ForumCategoryDtoMapper {
    // POST / PUT
    ForumCategoryServiceModel forumCategoryDtoToForumCategoryServiceModel(ForumCategoryDto forumCategoryDto);

    // GET ALL / GET BY ID
    ForumCategoryDto forumCategoryServiceModelToForumCategoryDto(ForumCategoryServiceModel forumCategoryServiceModel);
}
