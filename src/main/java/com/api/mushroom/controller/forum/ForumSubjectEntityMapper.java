package com.api.mushroom.controller.forum;

import com.api.mushroom.entity.ForumSubjectEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ForumSubjectEntityMapper {

    ForumSubjectEntity toEntity(ForumSubjectDto forumSubjectDto);

    @AfterMapping
    default void linkForumCommentaryEntities(@MappingTarget ForumSubjectEntity forumSubjectEntity) {
        forumSubjectEntity.getForumCommentaryEntities().forEach(forumCommentaryEntity -> forumCommentaryEntity.setForumSubjectEntity(forumSubjectEntity));
    }

    ForumSubjectDto toDto(ForumSubjectEntity forumSubjectEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ForumSubjectEntity partialUpdate(ForumSubjectDto forumSubjectDto, @MappingTarget ForumSubjectEntity forumSubjectEntity);
}