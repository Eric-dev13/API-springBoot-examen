package com.api.mushroom.controller.forum.dto;

import com.api.mushroom.entity.ForumSubjectEntity;

import java.time.LocalDateTime;
import java.util.List;

public record ForumCategoryDto (
        Long id,
        LocalDateTime createdAt,
        String name
        //List<ForumSubjectDto> forumSubjects
        ){ }
