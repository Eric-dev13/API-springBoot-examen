package com.api.mushroom.controller.forum.mapper;

import com.api.mushroom.controller.forum.dto.ForumCategoryDto;
import com.api.mushroom.controller.forum.dto.ForumCommentaryDto;
import com.api.mushroom.service.forum.model.ForumCategoryServiceModel;
import com.api.mushroom.service.forum.model.ForumCommentaryServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ForumCommentaryDtoMapper {
    // POST / PUT
    @Mapping(target="forumSubject.forumCommentaries", ignore = true)
    ForumCommentaryServiceModel forumCommentaryDtoToForumCommentaryServiceModel(ForumCommentaryDto forumCommentaryDto);

    // GET ALL / GET BY ID
    ForumCommentaryDto forumCommentaryServiceModelToForumCommentaryDto(ForumCommentaryServiceModel forumCommentaryServiceModel);
}
