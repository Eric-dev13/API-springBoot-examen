package com.api.mushroom.service.forum.model;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ForumCategoryServiceModel {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private List<ForumSubjectServiceModel> forumSubjects = new ArrayList<>();
}
