package com.api.mushroom.controller.forum.dto;

import com.api.mushroom.entity.ForumCategoryEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record ForumSubjectDto(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String title,
        String description,
        ForumUserDto user,
        List<ForumCategoryDto> forumCategories,
        List<ForumCommentaryDto> comments
) { }
