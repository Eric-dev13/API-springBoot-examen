package com.api.mushroom.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ForumSubjectDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String title;
    private String description;
    private UserDto user;
    private List<Long> categoriesId;
    private List<ForumCategoryDto> forumCategories;
    private List<ForumCommentaryDto> forumCommentaries;
}
