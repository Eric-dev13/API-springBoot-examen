package com.api.mushroom.controller.forum.dto;

import com.api.mushroom.entity.ForumCategoryEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ForumSubjectAddDto{
        @NotBlank
        private String title;
        @NotBlank
        private String description;
        private List<ForumCategoryDto> forumCategories;
}
