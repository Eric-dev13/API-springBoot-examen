package com.api.mushroom.service.forum.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ForumSubjectServiceModel {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String title;
    private String description;
    private ForumUserServiceModel user;
    private List<ForumCategoryServiceModel> forumCategories = new ArrayList<>();
    private List<ForumCommentaryServiceModel> comments = new ArrayList<>();
}
