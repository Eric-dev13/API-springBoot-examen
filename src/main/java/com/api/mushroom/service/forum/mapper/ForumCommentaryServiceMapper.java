package com.api.mushroom.service.forum.mapper;

import com.api.mushroom.entity.ForumCommentaryEntity;
import com.api.mushroom.service.forum.model.ForumCommentaryServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ForumCommentaryServiceMapper {
    // POST / PUT
    ForumCommentaryEntity addForumCategoryServiceModelToForumCategoryEntity(ForumCommentaryServiceModel forumCommentaryServiceModel);
}
