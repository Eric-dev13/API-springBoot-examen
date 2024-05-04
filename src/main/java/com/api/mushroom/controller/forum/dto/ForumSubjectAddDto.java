package com.api.mushroom.controller.forum.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ForumSubjectAddDto{
        @NotBlank
        private String title;

        @NotBlank
        private String description;

        private List<Long> categoriesId;

        //private List<ForumCategoryDto> forumCategories = new ArrayList<>();
}
