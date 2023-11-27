package com.api.mushroom.controller.forum.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ForumCategoryGetDto(
        Long id,
        LocalDateTime createdAt,
        String name,
        List<ForumSubjectDto> forumSubjects
        ){ }
